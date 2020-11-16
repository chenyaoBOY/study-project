package org.crazy.thread.advanced.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenyao
 * @date 2019/6/17 17:40
 * @description 可重入锁超时设置
 */
public class ReentrantLockTimeout implements Runnable{

    static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if(lock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(7000);
            }else{
                System.out.println(Thread.currentThread().getName()+"线程获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ReentrantLockTimeout()).start();
        new Thread(new ReentrantLockTimeout()).start();
    }
}
