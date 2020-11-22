package com.study.jdk.eight.str;



import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author chenyao
 * @date 2020/8/6 14:54
 * @description
 */
public class StringFeature {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String a =  new String("a");
        String b =  new String("a");
        System.out.println("a==b?"+(a == b));
        System.out.println("a.equals(b)?"+a.equals(b));

        int i = "abcdefg".codePointCount(0,1);
        System.out.println(i);
        byte[] utf8s = "123".getBytes(StandardCharsets.UTF_16);

        System.out.println(utf8s.length);
    }
}
