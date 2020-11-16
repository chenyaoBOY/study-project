package org.crazy.thread.advanced.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenyao
 * @date 2019/6/eight 10:51
 * @description ReentrantLock 和Condition的配合使用 可以做到已经获得锁的线程等待 然后再唤醒
 *
 *      在阻塞队列中  {@link java.util.concurrent.ArrayBlockingQueue} 使用了该方式 进行阻塞和唤醒
 */
public class ConditionAndLock implements Runnable{
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    @Override
    public void run() {
        try {
            System.out.println("lock准备获得锁");
            lock.lock();
            Thread.sleep(1000);
            System.out.println("condition准备释放锁");
            condition.await();//释放锁 并阻塞
            System.out.println("lock重新获得锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("lock释放锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ConditionAndLock()).start();
        Thread.sleep(200);
        lock.lock();
        System.out.println("lock在main线程中获得了锁");
        condition.signal();//唤醒被condition阻塞的线程
        System.out.println("-------------");
        lock.unlock();
        System.out.println("结束");
    }
}
