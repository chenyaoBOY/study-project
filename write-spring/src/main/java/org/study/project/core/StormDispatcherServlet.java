package org.study.project.core;

import org.study.project.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 *  模拟springmvc
 *      需要实现的细节：
 *             1.加载配置文件 实现扫描功能
 *             2.初始化bean 放入容器中
 *             3.注入bean  实现ioc功能
 *             4.初始化handlerMapping，映射url路径 到对应的bean和方法
 *             5.通过用户url反射执行对应方法
 */
public class StormDispatcherServlet extends HttpServlet {

    private Properties prop = new Properties();
    private List<String> classNames = new ArrayList<String>();
    private static Map<String,Object> beanMap = new HashMap<String,Object>();
    private List<Handler> handlers = new ArrayList<Handler>();



    @Override
    public void init(ServletConfig config) throws ServletException {


//        1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfig"));
//        2.扫描带注解的bean
        doScannerPackage(prop.getProperty("scannerPackage"));
//        3.初始化bean 生成实例 key:value
        doInitBeans();
//        4.依赖注入
        doAutowired();
//        ================完成springIOC功能===================

//        5.初始化handlerMapping   关联
        doHandlerMappingRelating();
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (handlers.isEmpty()) {
            throw new RuntimeException("404Not Found");
        }
//        1.获取用户url，截取之后 遍历所有的路径
        //1.1 没有该路径  报错
        //1.2 有该路径    参数匹配 反射执行方法  并返回值
        try{

            String contextPath = request.getContextPath();
            String requestUrl = request.getRequestURL().toString();
            String[] split = requestUrl.split(contextPath);
            if(split.length==1){//访问的项目根路径
                request.getRequestDispatcher("hello.jsp").forward(request,response);
            }


            boolean b=true;
            for (Handler h : handlers) {
                if(!split[1].equals(h.getPath())){continue;}
                Method method = h.getMethod();

                String username = request.getParameter("username");
                String password = request.getParameter("password");

                Class<?>[] types = method.getParameterTypes();
                Object[] args = new Object[types.length];
                args[0]=username;
                args[1]=password;

                Object result = method.invoke(h.obj, args);
                response.getWriter().print(result);

                b=false;break;
            }
            if(b){
                throw new RuntimeException("404Not Found");
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }



    //===============================================

    private void doHandlerMappingRelating() {
        if (beanMap.isEmpty()) {
            return;
        }
        //依赖注入完毕后  获取cyRequestMapping注解上的路径
        for (String key : beanMap.keySet()) {
            Class<?> clazz = beanMap.get(key).getClass();
            if(clazz.isAnnotationPresent(CyController.class)){
                //先获取类上的路径
                String url="";
                CyRequestMapping cyRequestMapping = clazz.getAnnotation(CyRequestMapping.class);
                if (cyRequestMapping != null) {
                    url="/"+cyRequestMapping.value();
                }
                //在获取方法上的路径
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    String path="";
                    if(method.isAnnotationPresent(CyRequestMapping.class)){
                        String value = method.getAnnotation(CyRequestMapping.class).value();
                        if(value.lastIndexOf("/")==value.length()-1){
                            value=value.substring(0,value.length()-1);
                        }
                        path =(url+value).replaceAll("/+", "/");
                        //每一个路径 需要存储   对应 路径的方法 、类、 和url
                        Handler hand = new Handler();
                        hand.setMethod(method);
                        hand.setClazz(clazz);
                        hand.setPath(path);
                        hand.obj = beanMap.get(key);
                        handlers.add(hand);
                    }

                }
            }

        }

    }
    //内部类
    public class Handler{
        private Method method;
        private Class clazz;
        private String path;
        private Object obj;

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public Class getClazz() {
            return clazz;
        }

        public void setClazz(Class clazz) {
            this.clazz = clazz;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

    private void doAutowired() {
        if(beanMap.isEmpty()){return;}
        //bean注入
        try{
            for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
                //获取类的成员变量
                Field[] fields = entry.getValue().getClass().getDeclaredFields();
                for (Field field : fields) {
                    String name = field.getType().getName();

                    //如果成员变量上加了CyAutowired注解
                    if (field.isAnnotationPresent(CyAutowired.class)) {
                        CyAutowired cyAutowired = field.getAnnotation(CyAutowired.class);
                        String value = cyAutowired.value().trim();
                        field.setAccessible(true);
                        //如果没有指定bean
                        if("".equals(value)){
                            String beanId ="";
                            Class<?> fieldClazz = field.getType();
                            if(fieldClazz.isInterface()){//如果是接口的话  需要找到实现该接口的实现类
                                beanId=firstLowerBeanName(fieldClazz.getSimpleName())+"Impl";
                            }else{
                                beanId =  firstLowerBeanName(field.getType().getSimpleName());
                            }
                            if(beanMap.get(beanId) == null){
                                throw  new RuntimeException(entry.getValue().getClass()+"=="+field.getName()+":无法注入bean，是否加入mvc管理？");
                            }
                            field.set(entry.getValue(),beanMap.get(beanId));
                        }else{
                            if(beanMap.get(value) == null){
                                throw  new RuntimeException(entry.getValue().getClass()+"=="+field.getName()+":未找到指定Bean:"+value);
                            }
                            field.set(entry.getValue(),beanMap.get(value));
                        }

                    }
                }
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }


    }

    public ArrayList<Class> getImplBeans(File file){
        ArrayList<Class> list = new ArrayList<Class>();
        for (File f : file.listFiles()) {
            if(f.isDirectory()){
                ArrayList<Class> classes = getImplBeans(f);
                list.addAll(classes);
            }else{
                list.add(f.getClass());
            }
        }
        return list;
    }
    private void doInitBeans() {

        try{
            //判断是否有需要初始化的bean
            if(classNames.isEmpty()){return;}

            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);//获取到bean的Class字节码文件

                //判断带有注解的bean
                if(clazz.isAnnotationPresent(CyController.class)){
                    getBean(clazz.getAnnotation(CyController.class).value().trim(),clazz);
                }else if (clazz.isAnnotationPresent(CyService.class)){
                    getBean(clazz.getAnnotation(CyService.class).value().trim(),clazz);
                }else if (clazz.isAnnotationPresent(CyRepository.class)){
                    getBean(clazz.getAnnotation(CyRepository.class).value().trim(),clazz);
                }else if (clazz.isAnnotationPresent(CyComponent.class)){
                    getBean(clazz.getAnnotation(CyComponent.class).value().trim(),clazz);
                }

            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public String firstLowerBeanName(String  str){
        char[] chars = str.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }
    public void getBean(String value,Class clazz){
        try{
            if(clazz.isInterface()){
                throw new RuntimeException(clazz.getName()+":接口不能");
            }
            //判断 注解是否自定义value值
            if("".equals(value)){
                //使用类名 首字母小写的格式
                String beanId = firstLowerBeanName(clazz.getSimpleName());
                beanMap.put(beanId,clazz.newInstance());
            }else{
                //判断用户自定义值 是否存在重复  如果重复值 需要抛出异常
                if(beanMap.get(value)!=null){
                    throw new RuntimeException(clazz.getName()+":注解value值不唯一！");
                }
                beanMap.put(value,clazz.newInstance());
            }
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }

    private void doScannerPackage(String location) {
        //获取target目录下的class文件 this.getClass().getResource() = d：/target/classes/
        URL url = this.getClass().getResource("/"+location.replaceAll("\\.","/"));
        String urlFile = url.getFile();
        File file = new File(urlFile);
        File[] files = file.listFiles();

        for (File f : files) {
            if(f.isDirectory()){
                doScannerPackage(location+"."+f.getName());
            }else{
                classNames.add(location+"."+f.getName().replace(".class",""));
            }
        }
    }

    private void doLoadConfig(String configLocation) {
        //获取配置文件名称  并读取该文件 获取输入流    通过类加载器获取到配置文件的流
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(configLocation);
        try{
            prop.load(in);//将properties文件内容  读取到Properties中   key:value形式
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(in != null){
                try{
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
