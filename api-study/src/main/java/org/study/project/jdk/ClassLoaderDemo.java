package org.study.project.jdk;

/**
 * @author chenyao
 * @date 2020/3/19 11:18
 * @description
 */
public class ClassLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException {
//        Class<?> clazz = Class.forName("org.study.project.jdk.Demo");
//
//        System.out.println(clazz.getName());

        Class<?> aClass = ClassLoaderDemo.class.getClassLoader().loadClass("org.study.project.jdk.ClassLoaderDemo");
//        System.out.println(aClass.getName());
//        Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass("org.study.project.jdk.Demo");
        System.out.println(aClass.getSimpleName());
    }

}
