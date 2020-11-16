package org.study.project.jdk;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author chenyao
 * @date 2020/3/19 14:31
 * @description
 */
public class RedBag {
    public static void main(String[] args) {
        BigDecimal total = new BigDecimal(100);
        BigDecimal max = total.multiply(new BigDecimal(0.3));
        BigDecimal min = new BigDecimal(1);
        BigDecimal personNum = new BigDecimal(10);

        BigDecimal rest = total.subtract(min.multiply(personNum));

        double[] result = new double[personNum.intValue()];
        for (int i = 0; i < personNum.intValue(); i++) {
            double v = Math.random() * max.doubleValue() - 1;
            while (v > max.doubleValue() || v < 0) {
                v = Math.random() * max.doubleValue();
            }
            if (v > rest.doubleValue()) {
                v = rest.doubleValue();
            }
            BigDecimal value = new BigDecimal(v).setScale(2, BigDecimal.ROUND_DOWN);
            result[i] = value.add(min).doubleValue();//+1
            rest = rest.subtract(value);
        }
        System.out.println(Arrays.toString(result));
    }
}
