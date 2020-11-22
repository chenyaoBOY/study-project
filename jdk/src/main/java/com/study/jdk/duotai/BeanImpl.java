package com.study.jdk.duotai;

/**
 * @author chenyao
 * @date 2020/5/21 10:43
 * @description
 */
public class BeanImpl extends AbstractBean {


    public void print(){
        System.out.println("--------------------");
    }

    public static void main(String[] args) {
        BeanImpl bean = new BeanImpl();
    }
}
