package com.study.jdk.entity;

import java.lang.annotation.*;

/**
 * @author chenyao
 * @date 2021/2/22 10:16
 * @description
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MyAnno {
}
