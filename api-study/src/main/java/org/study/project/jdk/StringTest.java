package org.study.project.jdk;

import java.lang.reflect.*;

/**
 * @author chenyao
 * @date 2019/10/8 15:54
 * @description
 */
public class StringTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

        String str = "asfg" + "asdfb";
        Class<?> clazz = Class.forName("java.lang.String");
        Object o = clazz.newInstance();
        String a = (String) o;
        System.out.println(a.length());
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            constructor.newInstance();
        }
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            method.getName().equals("get");
            method.invoke(o,"dd");
        }
        Field[] fields = clazz.getFields();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
        }

    }
}
