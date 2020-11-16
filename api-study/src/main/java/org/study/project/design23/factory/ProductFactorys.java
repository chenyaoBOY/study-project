package org.study.project.design23.factory;

/**
 * @author chenyao
 * @date 2019/8/23 17:09
 * @description
 */
public class ProductFactorys {

    public static AbstractProduct newProduct(AbstractFactory factory) {
        return factory.newProduct();
    }

    public static <T> T newFactory(Class<T> clazz) {
        Object o = null;
        try {
            o = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (o instanceof AbstractFactory) {
            return (T)o;
        }else{
            throw new RuntimeException("类型不符");
        }
    }
    public static AbstractProduct newProduct(Class<? extends AbstractFactory> clazz) {
        Object o = null;
        try {
            o = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (o instanceof AbstractFactory) {
            return ((AbstractFactory) o).newProduct();
        }else{
            throw new RuntimeException("类型不符");
        }
    }


}
