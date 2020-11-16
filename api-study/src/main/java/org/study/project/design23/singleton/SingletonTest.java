package org.study.project.design23.singleton;

/**
 * @author chenyao
 * @date 2019/11/27 10:23
 * @description
 */
public class SingletonTest {
    public static void main(String[] args) {
        SingletonUserInnerClass.Singleton single = SingletonUserInnerClass.getSingle();
        SingletonUserInnerClass.Singleton single2 = SingletonUserInnerClass.getSingle();
        SingletonUserInnerClass.Singleton single3 = SingletonUserInnerClass.getSingle();
        SingletonUserInnerClass.Singleton single4 = SingletonUserInnerClass.getSingle();
    }
}
