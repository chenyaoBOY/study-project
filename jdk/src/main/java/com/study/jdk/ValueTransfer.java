package com.study.jdk;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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

        String s = "1"+"2";
        String s2 = new String(s);

        System.out.println(s.equals(s2));
        System.out.println(s == s2 );

    }

    public static class ExceptionGotHandler  implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

}
