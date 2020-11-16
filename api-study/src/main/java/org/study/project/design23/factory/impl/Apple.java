package org.study.project.design23.factory.impl;

import org.study.project.design23.factory.AbstractProduct;

/**
 * @author chenyao
 * @date 2019/8/23 17:07
 * @description
 */
public class Apple implements AbstractProduct {
    private String name = "apple";
    private int price = 99;
    @Override
    public String getProductName() {
        return getName();
    }

    @Override
    public int getProductPrice() {
        return getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
