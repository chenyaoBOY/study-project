package org.crazy.thread.advanced.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenyao
 * @date 2019/6/28 15:49
 * @description
 *      ReentrantLock释放锁的时候，如果lock的占有线程和释放线程不一致 会抛出异常IllegalMonitorStateException
 */
public class IllegalMonitorStateExceptionTest extends Thread{

    static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
    }

    public static void main(String[] args) throws InterruptedException {
        IllegalMonitorStateExceptionTest t = new IllegalMonitorStateExceptionTest();
        t.start();
        Thread.sleep(1000);
        lock.unlock();
    }
}
