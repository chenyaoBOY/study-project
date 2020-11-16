package com.study.jdk.seven;

/**
 * @author chenyao
 * @date 2020/3/31 11:20
 * @description
 */
public class TryFinnallyReturn {

    public static void main(String[] args) {

        System.out.println(methodA());
    }

    private static int method() {
        int i;
        try {
            i=1;
            return i;
        }catch (Exception e){
            return 0;
        }finally {
            System.out.println("finally块执行了");
            i=10;
            return i;
        }
    }
    private static String methodA() {
        String i;
        try {
            i="str";
            System.out.println("try执行");
            return i;
        }catch (Exception e){
            return "";
        }finally {
            i="str++";
            System.out.println("finally块执行了");
//            return i;
        }
    }
}
