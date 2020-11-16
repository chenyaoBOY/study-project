package org.crazy.thread.advanced.threadsafe;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 闭锁的实现
 */
public class AtresiaCloseLock {

    /**
     * CountDownLatch
     *      闭锁：可以在一个或多个线程中 阻塞等待一组事件发生
     * @throws InterruptedException
     */
    @Test
    public void countDonwLatch() throws InterruptedException {

        CountDownLatch openDoor = new CountDownLatch(1);
        int maxThreadCount = 10;
        CountDownLatch closeDoor = new CountDownLatch(maxThreadCount);

        for (int i = 0; i < maxThreadCount; i++) {
            System.out.println("执行顺序"+i);
           new Thread( new Runnable() {
               @Override
               public void run() {
                   try{
                       openDoor.await();//等待计数器清零 否则阻塞
                       try{
                           System.out.println(Thread.currentThread().getName());//线程任务
                       }finally {
                           closeDoor.countDown();//每运行一个线程 闭门计数器递减
                       }
                   }catch (InterruptedException e){
                       e.printStackTrace();
                   }

               }
           }).start();
           Thread.sleep(50);
        }

        long start = System.nanoTime();
        openDoor.countDown();//统一执行开门事件
        closeDoor.await();//等待所有线程执行完毕  关门事件
        long end = System.nanoTime();

        System.out.println(end - start);


        int n=8;
        for (int i = 0; i < n; i++) {
            new Thread( new Runnable() {
                @Override
                public void run() {
                    System.out.println(123);
                }
            }).start();
        }
    }

    /**
     * FutureTask 也可以实现闭锁的效果
     */
    @Test
    public void futureTask(){

        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String name = Thread.currentThread().getName();
                return name;
            }
        });

        new Thread(futureTask).start();

        try{
            String s = futureTask.get();
            System.out.println(s);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }

    }

    @Test
    public void semaphore1(){


        for (int i = 0; i < 10; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(123);
                }
            }).start();



        }
    }
}
