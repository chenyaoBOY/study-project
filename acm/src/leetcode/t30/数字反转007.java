package leetcode.t30;

/**
 * @author chenyao
 * @date 2019/9/11 14:12
 * @description 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 */
public class 数字反转007 {

    /**
     * 1字符串反转方法 效率低
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        char[] chars = String.valueOf(x).toCharArray();
        boolean flag = true;
        int n = chars.length;
        boolean sympol = chars[0] == '-' ? true : false;
        for (int i = n - 1; i >= n / 2; i--) {
            if (chars[i] == 0 && flag) {
                continue;
            }
            char temp = chars[i];
            chars[i] = chars[n - i - 1];
            chars[n - i - 1] = temp;
            flag = false;
        }
        String res = String.valueOf(chars);
        if (sympol) {
            res = res.replace("-", "");
        }
        long val = Long.valueOf(res);
        if (val > Integer.MAX_VALUE) {
            return 0;
        }
        if (sympol) {
            return -Integer.valueOf(String.valueOf(val));
        }
        return Integer.valueOf(String.valueOf(val));
    }

    public int reverse2(int x) {
        char[] s = String.valueOf(x).toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean falg = s[0] == '-' ? true : false;
        for (int i = s.length - 1; i >= 0; i--) {
            if (i == 0 && falg) {
                continue;
            }
            if (i == s.length - 1 && falg) {
                sb.append("-");
            }
            sb.append(s[i]);
        }

        long val = Long.valueOf(sb.toString());
        if (val > Integer.MAX_VALUE || val < Integer.MIN_VALUE) {
            return 0;
        }
        return Integer.valueOf(String.valueOf(val));
    }

    public int reverse3(int x) {
        //  47868 反转 先取模 得到最后一位数8 加到res=0上 此时res=8 下次再取模得到6 res= res*10+6=86 以此类推
        int res = 0;
        while (x != 0) {
            int next = x % 10;
            if(res > Integer.MAX_VALUE /10 || (res==Integer.MAX_VALUE/10 && next > 7)) return 0;
            if(res< Integer.MIN_VALUE/10 || (res==Integer.MIN_VALUE/10 && next < -8)) return 0;
            res = res * 10 + next;
            x = x / 10;
        }
        return res;

    }

}
