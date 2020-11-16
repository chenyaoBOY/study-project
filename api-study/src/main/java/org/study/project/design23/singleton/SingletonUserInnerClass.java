package org.study.project.design23.singleton;

/**
 * @author chenyao
 * @date 2019/11/27 10:30
 * @description
 */
public class SingletonUserInnerClass {

    public static Singleton getSingle(){
        return Singleton.singleton;
    }


     static class Singleton {
        private Singleton(){
            System.out.println("内部静态类初始化");
        }
        private static Singleton singleton = new Singleton();
    }
}
