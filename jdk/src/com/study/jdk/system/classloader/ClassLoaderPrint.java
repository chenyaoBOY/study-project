package com.study.jdk.system.classloader;

/**
 * @author chenyao
 * @date 2019/12/13 13:58
 * @description
 *
 *  https://blog.csdn.net/briblue/article/details/54973413
 * 上述文章对双亲委派机制 介绍的即为详细
 */
public class ClassLoaderPrint {
    public static void main(String[] args) {
        ClassLoader classLoader = CreateBySelf.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(int.class.getClassLoader());
    }
}
