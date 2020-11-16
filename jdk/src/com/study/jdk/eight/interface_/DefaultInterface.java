package com.study.jdk.eight.interface_;

/**
 * @author chenyao
 * @date 2019/12/9 17:29
 * @description
 */
public interface DefaultInterface {

    default void print(){
        System.out.println("jdk1.8新特性之。" +
                "默认方法允许将新功能添加到库的接口中，并确保兼容实现老版本接口的旧有代码。");
    }
}
