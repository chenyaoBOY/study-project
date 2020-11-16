package org.study.project.annotation;


import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CyRequestParam {
    String value() default "";
    String defaultValue() default "";
}
