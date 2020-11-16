package org.study.project.design23.factory;

import org.study.project.design23.factory.impl.AppleFactory;

/**
 * @author chenyao
 * @date 2019/8/23 17:10
 * @description
 */
public class FactoryTest {
    public static void main(String[] args) {
        AbstractProduct product = ProductFactorys.newProduct(new AppleFactory());
        AbstractProduct appleFactory = ProductFactorys.newProduct(AppleFactory.class);
        AppleFactory factory = ProductFactorys.newFactory(AppleFactory.class);
        System.out.println(factory.newProduct());

        System.out.println(product);
        System.out.println(appleFactory);
    }
}
