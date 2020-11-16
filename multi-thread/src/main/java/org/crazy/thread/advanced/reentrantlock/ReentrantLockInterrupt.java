package org.crazy.thread.advanced.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenyao
 * @date 2019/6/17 17:22
 * @description
 *      基于可中断的可重入锁ReentrantLock 中断死锁的情况
 */
public class ReentrantLockInterrupt implements Runnable{

    static ReentrantLock lock1 = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();
    int lockType;

    public ReentrantLockInterrupt(int lockType) {
        this.lockType = lockType;
    }

    @Override
    public void run() {
        try {
            if(lockType == 1){
                try {
                    lock1.lockInterruptibly();
                    Thread.sleep(500);
                    System.out.println("已获取lock1并尝试获取lock2");
                    lock2.lockInterruptibly();
                    System.out.println("已获取lock1、lock2");
                } catch (InterruptedException e) {}
            }else{
                try {
                    lock2.lockInterruptibly();
                    Thread.sleep(500);
                    System.out.println("已获取lock2并尝试获取lock1");
                    lock1.lockInterruptibly();
                    System.out.println("已获取lock2、lock1");
                } catch (InterruptedException e) {}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getName()+"退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ReentrantLockInterrupt(1));
        Thread t2 = new Thread(new ReentrantLockInterrupt(2));
        t1.start();t2.start();
        Thread.sleep(2000);
        t2.interrupt();
    }
}
