package org.study.project.design23.construct.example;

/**
 * @author chenyao
 * @date 2019/8/26 16:13
 * @description
 */
public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    //产品构建与组装方法
    public Product construct() {
        builder.build();
        return builder.getProduct();
    }
}
