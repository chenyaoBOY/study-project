package com.storm.apigw.utils;

import com.alibaba.fastjson.JSONObject;
import com.storm.apigw.annotation.Api;
import com.storm.apigw.exception.ApiException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class ApiUtils {
    private static ApplicationContext CONTEXT ;
    private static final Logger logger = Logger.getLogger(ApiUtils.class);
    private static ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
    static {
        CONTEXT = new FileSystemXmlApplicationContext("classpath:springmvc-base.xml");
    }

    public static String invokeApi(String methodName,String params) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Map map = null;
        try{
            map = (Map<String,Object>) JSONObject.parse(params);
        }catch (Exception e){
            e.printStackTrace();
            throw new ApiException("params参数请传入标准json格式！");
        }

        Map<String, Object> beans = CONTEXT.getBeansWithAnnotation(Api.class);

        if(beans!=null && beans.size()>0){
            for (String s : beans.keySet()) {
                String result = invokeMethod(beans.get(s),methodName, map);
                if(!"500".equals(result)){
                    return result;
                }
            }
        }
        logger.debug("500错误！ApiUtils 22行 未获取到被api注解的类");
        throw new ApiException("系统出错，请稍后重试！");
    }

    public static String invokeMethod(Object owner,String methodName,Map<String,Object> args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method[] methods = owner.getClass().getMethods();//获取所有方法
        String result = "";
        for (Method method : methods) {//遍历方法
            if(method.getName().equals(methodName)){//若方法名和参数方法名相同
                String[] parameterNames = discoverer.getParameterNames(method);//获取该方法上的参数名称
                Class<?>[] parameterTypes = method.getParameterTypes();//获取该方法上的参数类型
                if(parameterNames.length==0){
                    return (String) method.invoke(owner);
                }
                if(parameterNames.length!=args.size()){
                    throw new ApiException("方法名："+methodName+"传入的参数个数错误！参数名称应传入："+ Arrays.toString(parameterNames));
                }

                Object[] invokeArgs = new Object[parameterNames.length];

                for (int i = 0; i <parameterNames.length ; i++) {
                    String argsName = parameterNames[i];
                    if(!args.containsKey(argsName)){
                        throw new ApiException("参数名："+argsName+"传入有误！params参数中未包含该参数！");
                    }

                    String argsValue = (String)args.get(argsName);

                    Object realArgsValue  = judgeArgsType(parameterTypes[i],argsName,argsValue);
                    invokeArgs[i] = realArgsValue;

                }

                result = (String)method.invoke(owner,invokeArgs);
                return result;
            }
        }
        return "500";

    }

    private static Object judgeArgsType(Class<?> parameterType,String argsName,String argsValue) {
        Object obj =null;
        try {
            if(String.class.equals(parameterType)){
                obj=argsValue;
            }else if(byte.class.equals(parameterType)){
                obj=Byte.parseByte(argsValue);
            }else if(Byte.class.equals(parameterType)){
                obj=Byte.parseByte(argsValue);
            }else if(boolean.class.equals(parameterType)){
                obj=Boolean.parseBoolean(argsValue);
            }else if(Boolean.class.equals(parameterType)){
                obj=Boolean.parseBoolean(argsValue);
            }else if(short.class.equals(parameterType)){
                obj=Short.parseShort(argsValue);
            }else if(Short.class.equals(parameterType)){
                obj=Short.parseShort(argsValue);
            }else if(int.class.equals(parameterType)){
                obj=Integer.parseInt(argsValue);
            }else if(Integer.class.equals(parameterType)){
                obj=Integer.parseInt(argsValue);
            }else if(long.class.equals(parameterType)){
                obj=Long.parseLong(argsValue);
            }else if(Long.class.equals(parameterType)){
                obj=Long.parseLong(argsValue);
            }else if(float.class.equals(parameterType)){
                obj=Float.parseFloat(argsValue);
            }else if(Float.class.equals(parameterType)){
                obj=Float.parseFloat(argsValue);
            }else if(double.class.equals(parameterType)){
                obj=Double.parseDouble(argsValue);
            }else if(Double.class.equals(parameterType)){
                obj=Double.parseDouble(argsValue);
            }else if(Date.class.equals(parameterType)){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                obj=sdf.parse(argsValue);
            }else{
                
            }
        } catch (ClassCastException e) {
            throw new ApiException("参数名："+argsName+"类型有误！应传入"+parameterType+"类型！");
        } catch (ParseException e) {
            throw new ApiException("参数名："+argsName+"解析！应传入标准格式日期字符串！");
        }
        return obj;
    }
}
