package com.study.jdk.system.classloader;

/**
 * @author chenyao
 * @date 2019/12/9 10:19
 * @description
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        new Thread(()->{

        }).start();
        return super.loadClass(name, resolve);
    }
}
