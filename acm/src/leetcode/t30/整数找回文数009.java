package leetcode.t30;


/**
 * @author chenyao
 * @date 2019/9/11 16:27
 * @description 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class 整数找回文数009 {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        long len = String.valueOf(x).length();
        long LT = 10;
        long RT = Double.valueOf(Math.pow(10, len)).longValue();
        while (LT <= RT) {
            long L = (x % LT) / (LT / 10);
            long R;
            if ((x % RT) < (RT / 10)) {
                R = 0;
            } else {
                R = (x % RT) / (RT / 10);
            }
            if (L != R) return false;
            LT *= 10;
            RT /= 10;
        }
        return true;
    }

    public void test() {
        isPalindrome(1000000001);
//        System.out.println(54123 % 10000 / 1000);
    }

    public boolean test2(int x) {
        if (x < 0) return false;

        char[] c = String.valueOf(x).toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] != c[c.length - 1 - i]) return false;
        }
        return true;
    }
}
