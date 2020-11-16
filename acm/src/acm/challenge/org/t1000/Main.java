package acm.challenge.org.t1000;

import java.util.Scanner;

/**
 * @author chenyao
 * @date 2019/9/5 16:35
 * @description 输入a, b 输出 a+b;
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(add(sc.nextInt(), sc.nextInt()));
        System.out.println(sc.nextInt() + sc.nextInt());
    }

    public static int add(int a, int b) {

        int res = a;
        int xor = a ^ b;//得到原位和
        int forward = (a & b) << 1;//得到进位和
        if (forward != 0) {//若进位和不为0，则递归求原位和+进位和
            res = add(xor, forward);
        } else {
            res = xor;//若进位和为0，则此时原位和为所求和
        }
        return res;
    }

}