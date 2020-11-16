package org.crazy.thread.advanced.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;

/**
 * @author chenyao
 * @date 2020/1/21 16:36
 * @description
 */
public class MyAQSLock extends AbstractQueuedSynchronizer {
    public MyAQSLock() {
        super();
    }

    public void lock() {
        acquire(1);
    }

    public void unlock() {
        release(1);
    }

    @Override
    protected boolean tryAcquire(int arg) {

        if (compareAndSetState(0, arg)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        if (Thread.currentThread() != getExclusiveOwnerThread()) {
            System.out.println("当前线程解锁不是 独占线程");
            return false;
        }
        setState(0);
        setExclusiveOwnerThread(null);
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        MyAQSLock lock = new MyAQSLock();


        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "线程准备获取锁");
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "--------------获取到了锁--------------");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "--------------释放了锁-------------");
                lock.unlock();
            }).start();
        }


        Thread.sleep(300);
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "--------------获取到了锁--------------");
        lock.unlock();

    }
}
