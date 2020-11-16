package com.exception.util.response;

import com.exception.util.service.BussinessExceptionAssert;

/**
 * @author chenyao
 * @date 2020/5/4 21:27
 * @description
 */
public enum UserResponseEnum implements BussinessExceptionAssert {

    USER_ELLEGAL(500, "用户不合法"),
    USER_NOT_EXIST(10001, "用户不存在");


    private Integer code;
    private String msg;

    UserResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }



}
