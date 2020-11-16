package leetcode;


/**
 * @author chenyao
 * @date 2020/3/31 11:43
 * @description 16进制 32进制 8进制加法运算
 */
public class Sum {

    public static void main(String[] args) {

        String a = "4A";
        String b = "1";

        System.out.println(getNum(a)+getNum(b));

    }

    public static long getNum(String s) {
        s = s.toUpperCase();
        long sum = 0;
        int i = s.length();
        for (char c : s.toCharArray()) {
            i--;
            int n = getRealNum(c);
            sum += pow(n, i);
        }
        return sum;
    }

    private static long pow(int n, int i) {
        long sum = n;
        for (int j = 0; j < i; j++) {
            sum = 16 * sum;
        }
        return sum;
    }

    private static int getRealNum(char c) {
        switch (c) {
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
            default:
                return Integer.parseInt(String.valueOf(c));
        }
    }
}
