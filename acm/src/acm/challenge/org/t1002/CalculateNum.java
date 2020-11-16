package acm.challenge.org.t1002;

import java.math.BigDecimal;

/**
 * @author chenyao
 * @date 2020/3/16 18:09
 * @description
 */
public class CalculateNum {
    public static void main(String[] args) {
        BigDecimal sum = new BigDecimal(0);
        for (int i = 1; i <= 100; i++) {
            if (judge(i)) {
                sum = sum.add(jieCheng(i));
            }
        }
        System.out.println(sum);
    }

    public static BigDecimal jieCheng(int n) {
        if (n == 1) {
            return new BigDecimal(1);
        }
        return new BigDecimal(n).multiply(jieCheng(--n));
    }

    public static boolean judge(int n) {
        if (n == 1 || n == 2){
            return true;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
