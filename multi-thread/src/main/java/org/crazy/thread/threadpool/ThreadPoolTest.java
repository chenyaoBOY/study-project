package org.crazy.thread.threadpool;

import java.util.concurrent.*;

/**
 * @author chenyao
 * @date 2019/10/22 16:46
 * @description
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(123);
            }
        };
        Thread t = new Thread(runnable);
        t.start();
        t.start();
    }
}
