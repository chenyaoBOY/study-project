package org.study.project.design23.singleton;

/**
 * @author chenyao
 * @date 2019/11/27 10:30
 * @description
 */
public class SingletonUserInnerClass {
    public SingletonUserInnerClass() {
        System.out.println("主类被加载");
    }

    public static SingletonBean getSingle(){
        System.out.println("主类静态方法被调用");
        return Singleton.singleton;
    }


     static class Singleton {
         private static SingletonBean singleton = new SingletonBean();
         private Singleton(){
            System.out.println("内部静态类初始化");
        }
    }
}
