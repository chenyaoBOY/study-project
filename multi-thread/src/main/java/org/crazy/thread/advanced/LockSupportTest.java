package org.crazy.thread.advanced;

import java.util.concurrent.locks.LockSupport;

/**
 * @author chenyao
 * @date 2019/6/28 14:46
 * @description
 *
 * LockSupport.park();的阻塞方法 解除可以使用LockSupport.unpark(thread)
 * 或者使用线程中断
 */
public class LockSupportTest extends Thread{
    @Override
    public void run() {

        LockSupport.park();
        System.out.println("线程阻塞解除"+System.currentTimeMillis()/1000);
    }

    public static void main(String[] args) throws InterruptedException {
        LockSupportTest thread = new LockSupportTest();
        thread.start();
        System.out.println(System.currentTimeMillis()/1000);
        Thread.sleep(2000);
//        LockSupport.unpark(thread);
        thread.interrupt();

    }
}
