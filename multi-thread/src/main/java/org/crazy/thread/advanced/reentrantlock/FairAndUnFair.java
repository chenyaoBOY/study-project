package org.crazy.thread.advanced.reentrantlock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenyao
 * @date 2019/6/28 10:25
 * @description
 */
public class FairAndUnFair extends ReentrantLock implements Runnable {
    static FairAndUnFair lock = new FairAndUnFair(true);
    static CountDownLatch door = new CountDownLatch(3);

    public FairAndUnFair(boolean fair) {
        super(fair);
    }
    public FairAndUnFair() {
    }

    @Override
    public void run() {
        try {
            door.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            try {
                lock.lock();
                List<String> list = new LinkedList<>();
                Collection<Thread> threads = lock.getQueuedThreads();
                for (Thread thread : threads) {
                    list.add(thread.getName());
                }
                System.out.print("等待线程："+list.toString()+"  ");

                System.out.println(Thread.currentThread().getName() + " 获得了锁");

            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new FairAndUnFair()).start();
            door.countDown();
        }
    }


}
