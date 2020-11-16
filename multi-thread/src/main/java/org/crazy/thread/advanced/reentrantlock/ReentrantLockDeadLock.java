package org.crazy.thread.advanced.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenyao
 * @date 2019/6/17 17:45
 * @description 构建死锁的情况 使用reentrantlock 不会发生死锁
 */
public class ReentrantLockDeadLock implements Runnable{
    static ReentrantLock lock = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();
    int lockType;

    public ReentrantLockDeadLock(int lockType) {
        this.lockType = lockType;
    }

    @Override
    public void run() {
        if(lockType==1){
            while (true){
                if(lock.tryLock()){
                    try{
                        Thread.sleep(500);
                        if(lock2.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getName()+" 完成任务");
                                break;
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }else{
            while (true){
                if(lock2.tryLock()){
                    try{
                        Thread.sleep(500);
                        if(lock.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getName()+" 完成任务");
                                break;
                            } finally {
                                lock.unlock();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ReentrantLockDeadLock(1)).start();
        new Thread(new ReentrantLockDeadLock(2)).start();
    }
}
