package com.exception.util.exception;

import com.exception.util.service.IResponseEnum;

import java.text.MessageFormat;

/**
 * @author chenyao
 * @date 2020/5/4 20:56
 * @description
 */
public class BusinessException extends RuntimeException{
    public BusinessException(String msg) {
        super(msg);
    }

}
