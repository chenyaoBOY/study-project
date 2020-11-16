package org.crazy.thread.advanced.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chenyao
 * @date 2019/6/20 16:13
 * @description 读写锁的使用
 *
 *  实验证明：共享资源在没有写入的情况下，读的效率是非常快的，读的数据并不会过期，因为读的时候如果有写入则会发生阻塞，
 *  每次读取都是读取的最新数据
 *
 */
public class ReadAndWriteLockTest {
    //非读写锁
    static ReentrantLock bigLock = new ReentrantLock();
    //读写锁
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();
    int value;

    public int doRead(Lock readLock) throws InterruptedException {
        readLock.lock();
        try {
            Thread.sleep(1000);
            System.out.println("read"+System.currentTimeMillis()/1000+" value="+value);
            return value;
        } finally {
            readLock.unlock();
        }
    }

    public void doWrite(Lock writeLock, int add) throws InterruptedException {
        writeLock.lock();
        try {
            Thread.sleep(1000);
            value += add;
            System.out.println("write"+System.currentTimeMillis()/1000+" value="+value);
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadAndWriteLockTest rw = new ReadAndWriteLockTest();
        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                try {
                    rw.doRead(readLock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                try {
                    rw.doWrite(writeLock,1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    new Thread(writeTask).start();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    new Thread(readTask).start();
                }
            }
        }).start();

    }
}
