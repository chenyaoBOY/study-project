package leetcode;

/**
 * @author chenyao
 * @date 2020/3/31 12:06
 * @description 16进制 32进制 8进制加法运算
 */
public class Sum2 {

    public static void main(String[] args) {

        String a = "503C";
        String b = "40";
        StringBuilder sb = new StringBuilder();
        int location = 0;
        int jinwei = 0;
        int pow = 16;
        while (location < a.length() || location < b.length()) {
            int m, n;
            if (location >= a.length()) {
                m = 0;
            } else {
                m = getRealNum(a.charAt(a.length() - location - 1));
            }
            if (location >= b.length()) {
                n = 0;
            } else {
                n = getRealNum(b.charAt(b.length() - location - 1));
            }
            int res = (m + n + jinwei) % pow;
            jinwei = (m + n + jinwei) / pow;
            sb.append(getString(res));
            location++;
        }
        if(jinwei == 1){
            sb.append(1);
        }
        System.out.println(sb.reverse());
    }

    private static String getString(int res) {
        if (res < 10) return res + "";
        switch (res) {
            case 10:
                return "A";
            case 11:
                return "B";
            case 12:
                return "C";
            case 13:
                return "D";
            case 14:
                return "E";
            case 15:
                return "F";
            default:
                throw new RuntimeException("数据异常");
        }
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
