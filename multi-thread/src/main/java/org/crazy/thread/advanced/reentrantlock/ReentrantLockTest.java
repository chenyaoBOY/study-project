package org.crazy.thread.advanced.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenyao
 * @date 2019/11/19 10:44
 * @description
 */
public class ReentrantLockTest {
    public static final ReentrantLock LOCK = new ReentrantLock();
    public static void main(String[] args) {

        LOCK.lock();
        LOCK.unlock();
    }
}
