package com.exception.util.response;

import com.exception.util.service.BussinessExceptionAssert;

/**
 * @author chenyao
 * @date 2020/5/4 23:03
 * @description
 */
public enum  OrderResponseEnum implements BussinessExceptionAssert {

    ORDER_NOT_EXIST(100,"订单不存在");

    private Integer code;
    private String msg;

    OrderResponseEnum(Integer code, String msg) {
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
