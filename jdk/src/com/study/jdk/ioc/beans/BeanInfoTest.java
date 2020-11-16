package com.study.jdk.ioc.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * @author chenyao
 * @date 2019/12/30 16:57
 * @description
 */
public class BeanInfoTest {
    public static void main(String[] args) throws IntrospectionException {

        BeanInfo bi = Introspector.getBeanInfo(Person.class,Object.class);

        Stream.of(bi.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            System.out.println(propertyDescriptor);
        });


    }
}
