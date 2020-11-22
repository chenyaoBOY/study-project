package com.study.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private final Object obj;
    private Object proxyObj;
    public MyInvocationHandler(Object obj){
        this.obj = obj;
    }

    /**
     *
     * @param proxy  生成的代理类
     * @param method 代理类执行的方法
     * @param args   代理类执行该方法传入的参数
     * @return       自定义  一般是代理类执行该方法的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("前面 织入功能");

        Object invoke = method.invoke(obj, args);

        System.out.println("后面 织入功能");
        proxyObj = proxy;
        return invoke;
    }

    public Object getProxyObj(){
        return this.proxyObj;
    }
}
