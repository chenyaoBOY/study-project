package com.exception.util.service;

import com.exception.util.exception.BusinessException;

import java.text.MessageFormat;

/**
 * @author chenyao
 * @date 2020/5/4 22:58
 * @description
 */
public interface BussinessExceptionAssert extends Assert,IResponseEnum {

    default void assertNotNull(Object obj) {
        if (obj == null) {
            String msg = MessageFormat.format("code：{0};msg:{1}",
                    this.getCode().toString(), this.getMsg());
            throw new BusinessException(msg);
        }
    }

    default void assertNotNull(Object obj, String pattern, String... args) {
        if (obj == null) {
            String msg = MessageFormat.format(pattern,args);
            throw new BusinessException(msg);
        }
    }

}
