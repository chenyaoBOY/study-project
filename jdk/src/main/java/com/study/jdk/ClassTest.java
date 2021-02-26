package com.study.jdk;

import com.study.jdk.entity.ChildInterface;
import com.study.jdk.entity.MyAnno;


/**
 * @author chenyao
 * @date 2021/2/20 17:30
 * @description
 */
public class ClassTest {

    public static void main(String[] args) {

        System.out.println(ChildInterface.class.getInterfaces().length);

        MyAnno annotation = ChildInterface.class.getAnnotation(MyAnno.class);
        System.out.println(annotation);
        MyAnno declaredAnnotation = ChildInterface.class.getDeclaredAnnotation(MyAnno.class);
        System.out.println(declaredAnnotation);
    }
}
