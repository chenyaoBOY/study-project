package org.study.project.design23.construct.example;

/**
 * @author chenyao
 * @date 2019/8/26 16:12
 * @description
 */
public abstract class Builder {
    protected Product product = new Product();

    public abstract void buildPartA();

    public abstract void buildPartB();

    public abstract void buildPartC();

    //返回产品对象
    public Product getProduct() {
        return product;
    }

    public void build(){
        buildPartA();
        buildPartB();
        buildPartC();
    }
}
