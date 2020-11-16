package org.crazy.thread.base.theadlocal;

/**
 * @author chenyao
 * @date 2020/3/11 16:21
 * @description
 */
public class Demo4 {
    public static void main(String[] args) {
        method();
        System.gc();
    }
    private static void method() {
        for (int i = 0; i < 100; i++) {
            ThreadLocal<Integer> local = new ThreadLocal<>();
            local.set(i);
        }
    }
}
