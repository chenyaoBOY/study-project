package org.crazy.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author chenyao
 * @date 2020/1/21 13:52
 * @description
 */
public class ParkUnparkTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("---------m1---------");

        Thread t1 = new Thread(() -> {
            System.out.println("---------t1---------");
            LockSupport.park();
            System.out.println("---------t2---------");

        });
        t1.start();

        Thread.sleep(1000);
        System.out.println("---------m2---------");
        LockSupport.unpark(t1);
    }
}
