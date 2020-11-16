package org.study.project.design23.factory.impl;

import org.study.project.design23.factory.AbstractFactory;
import org.study.project.design23.factory.AbstractProduct;

/**
 * @author chenyao
 * @date 2019/8/23 17:07
 * @description
 */
public class AppleFactory implements AbstractFactory {
    @Override
    public AbstractProduct newProduct() {
        return new Apple();
    }
}
