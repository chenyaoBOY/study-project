package com.study.jdk.seven;

import java.math.BigDecimal;

/**
 * @author chenyao
 * @date 2020/3/31 17:15
 * @description
 */
public class Demo {
    public static void main(String[] args) {


        BigDecimal bd = BigDecimal.valueOf(60.12D);
        int i = bd.multiply(BigDecimal.valueOf(100D)).intValue();
        System.out.println(i);

    }
}
