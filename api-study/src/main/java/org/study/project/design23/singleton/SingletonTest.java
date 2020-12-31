package org.study.project.design23.singleton;

/**
 * @author chenyao
 * @date 2019/11/27 10:23
 * @description
 */
public class SingletonTest {
    public static void main(String[] args) {
        SingletonBean single = SingletonUserInnerClass.getSingle();
        single.print();
    }
}
