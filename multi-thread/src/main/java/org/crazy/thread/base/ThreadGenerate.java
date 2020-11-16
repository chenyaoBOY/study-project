package org.crazy.thread.base;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description //TODO 多线程的三种形式
 *                  1.继承
 *                  2.实现Runnable接口
 *                  3.实现Callable接口
 * @author chenyao
 * @date  2019/5/17 16:04
 */
public class ThreadGenerate {
    /**
     * 1.继承
     */
    class Thread4Extend extends Thread{
        private int cnt;
        @Override
        public void run() {
            for (int i = 0; i < 100000 ; i++) {
                cnt++;
            }
            System.out.println(Thread.currentThread().getName()+" cnt="+cnt);
        }
    }
    /**
     * 2.实现Runnable
     */
    class Thread4Runnable implements Runnable{
        private int cnt;
        @Override
        public void run() {
            for (int i = 0; i < 100000 ; i++) {
                cnt++;
            }
            System.out.println(Thread.currentThread().getName()+" cnt="+cnt);
        }
    }
    /**
     * 3.实现Callable
     */
    class Thread4Callable implements Callable<Integer>{
        private int cnt;
        @Override
        public Integer call() throws Exception {
            for (int i = 0; i < 100000 ; i++) {
                cnt++;
            }
            System.out.println(Thread.currentThread().getName()+" cnt="+cnt);
            return cnt;
        }
    }
    @Test
    public void testThread4Extend() throws InterruptedException {
        // todo 对于同一个线程类extend，共享变量就会发生线程安全问题
        Thread4Extend extend = new Thread4Extend();
        Thread thread = new Thread(extend);
        Thread thread2 = new Thread(extend);
        thread.start();thread2.start();
        thread.join();thread2.join();
    }
    @Test
    public void testThread4Runnable() throws InterruptedException {
        // todo 对于同一个线程类runnable，共享变量就会发生线程安全问题
        Thread4Runnable runnable = new Thread4Runnable();
        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread.start();thread2.start();
        thread.join();thread2.join();
    }
    @Test
    public void testThread4Callable() throws InterruptedException, ExecutionException {
        // todo 对于callable接口，task只会执行一次
        Thread4Callable callable = new Thread4Callable();
        FutureTask<Integer> task = new FutureTask<>(callable);
        FutureTask<Integer> task2 = new FutureTask<>(callable);
        Thread thread = new Thread(task);
        Thread thread4SameTask = new Thread(task);//同一个task只会执行一次
        Thread thread2 = new Thread(task2);
        thread.start();thread2.start();thread4SameTask.start();
        Integer integer = task.get();
        thread.join();thread2.join();thread4SameTask.join();
        System.out.println(task.get());
    }
}
