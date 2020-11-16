package com.study.jdk;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenyao
 * @date 2020/4/3 9:49
 * @description
 */
public class ValueTransfer {

    private   String msg;

    public ValueTransfer(String msg) {
        this.msg = msg;
    }

    public static void main(String[] args) throws ParseException {

        String s = null;
        System.out.println(s+"Sdf");

    }

    public static void change(ValueTransfer v1,ValueTransfer v2){

        ValueTransfer temp =v2;
        v2=v1;
        v1=temp;

    }


    @Override
    public String toString() {
        return "ValueTransfer{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
