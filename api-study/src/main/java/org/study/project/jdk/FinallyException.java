package org.study.project.jdk;

/**
 * @author chenyao
 * @date 2019/6/28 15:33
 * @description
 */
public class FinallyException {
    public static void main(String[] args) {
        test();
    }

    private static String test() {
        try {
            System.out.println("sadfads");
//            int i= 1/0;
//            return "sadfads";
        } finally {
            //即便使用return关键字 finally块中依然可以执行
            System.out.println(123);
        }
        System.out.println("finally后面执行");
        return "sadfads";
    }
}
