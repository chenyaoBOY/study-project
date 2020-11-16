package org.study.project.jdk;

import java.math.BigDecimal;

/**
 * @author chenyao
 * @date 2019/12/13 13:37
 * @description
 */
public class BigdecimalTest {
    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal(1.11234D);
        BigDecimal decimal3 = new BigDecimal("0.2");


        System.out.println(decimal.toString());
        System.out.println(decimal3.toString());
    }
}
