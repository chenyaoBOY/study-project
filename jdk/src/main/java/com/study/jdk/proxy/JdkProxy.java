package com.study.jdk.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {


    public static void main(String[] args) {

//        doJdkProxy1();
        doJdkProxy2();
    }
    public  static void doJdkProxy2(){
        //接口
        UserService service = new UserServiceImpl();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(service);
        //类加载器
        ClassLoader classLoader = JdkProxy.class.getClassLoader();
        //接口  获取方法
        Class<?>[] interfaces = service.getClass().getInterfaces();

        //生成代理类
        UserService serviceProxy = (UserService)Proxy.newProxyInstance(classLoader, interfaces, myInvocationHandler);
        //代理类执行方法  进行增强
        serviceProxy.getUser("陈瑶");

        Object proxyObj = myInvocationHandler.getProxyObj();
        System.out.println(serviceProxy==proxyObj);


    }
    public  static void doJdkProxy1(){
        final UserService userService = new UserServiceImpl();
        UserService service = (UserService)Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),//类加载器
                userService.getClass().getInterfaces(),//接口
                new InvocationHandler() {
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                        Object invoke;
                        if(method.getName().equals("getUser")){
                            System.out.println("执行输出前打印---");
                            invoke = method.invoke(userService, objects);
                            System.out.println("执行输出后打印");
                        }else{
                            invoke = method.invoke(userService, objects);
                        }
                        return invoke;
                    }
                });

        service.getUser("chenyao");
        service.getUserName();
    }
}
