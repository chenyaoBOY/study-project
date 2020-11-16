package org.study.project.annotation;

import java.lang.annotation.*;

/**
 * @author chenyao
 * @date 2019/12/12 11:32
 * @description
 *
 * public interface org.study.project.annotation.Hello extends java.lang.annotation.Annotation {
 *   public abstract java.lang.String value();
 * }
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Hello {
    String value() default "";
}
