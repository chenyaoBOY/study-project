package com.study.jdk;


/**
 * @author chenyao
 * @date 2021/2/24 18:11
 * @description
 */
public class StringByteTruncate {

    public static void main(String[] args) {

        String s = "chenyao";
        String name="陈瑶";
        System.out.println(new String(getByte(s,1)));
        System.out.println(new String(getByte(name,1)));
        System.out.println(new String(getByte(name,2)));
        System.out.println(new String(getByte(name,3)));
        System.out.println(new String(getByte(name,4)));

    }

    private static byte[] getByte(String s, int i) {
        byte[] bytes = s.getBytes();
        byte[] res = new byte[i];
        for (int j = 0; j < i; j++) {
            res[j] = bytes[j];
        }
        return res;
    }

}
