package org.crazy.thread.threadpool;

import java.util.concurrent.*;

/**
 * @author chenyao
 * @Description: jdk提供的线程池 Executors 研究
 * @date 2018/6/4/10:26
 */
public class ExecutorsThreadPool {

    /**
     * 创建3个线程的线程池
     */
    private final static ExecutorService executorService = Executors.newFixedThreadPool(3);
    /**
     * 创建可以被作为Target的线程任务
     */
    static class TaskOne implements Callable<Object>{
        @Override
        public Object call() throws Exception {
            Thread thread = Thread.currentThread();
            System.out.println("Callable线程："+thread.getName()+"   Task one 正在执行");
            return thread.getName();
        }
    }
    static class RunnableThread implements Runnable{
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            System.out.println("Runnable线程："+thread.getName()+"   Task one 正在执行");
        }
    }
    static class ExtendThread extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                Thread thread = Thread.currentThread();
                System.out.println("Thread线程："+thread.getName()+"   Task one 正在执行"+i);
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        executorsDemo();

        runSequence();

    }

    /**
     * 线程池的demo演示
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void executorsDemo() throws ExecutionException, InterruptedException {
        /**
         * callable形式
         *      实验发现：一个新建的线程任务，
         *      可以被线程池中的线程多次执行，但是如果是单个线程，线程执行完毕,再调用start方法无效
         */
        TaskOne task = new TaskOne();
        Future<Object> future = executorService.submit(task);
        Future<Object> future2 = executorService.submit(task);
        Future<Object> future3 = executorService.submit(task);
        Future<Object> future4 = executorService.submit(task);
        Future<Object> future5 = executorService.submit(task);

        System.out.println(future.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
        //通过shutdown方法，关闭进程，如果不关掉的话 虚拟机一直还在运行，线程没有死去
        executorService.shutdown();

    }
    /**
     * 线程池 如何保证线程的执行顺序
     */
    public static void runSequence() throws InterruptedException {
        /**
         * 实验发现
         *      使用Executors 貌似不能保证线程的执行顺序
         */
        ExtendThread extendThread1 = new ExtendThread();
        ExtendThread extendThread2 = new ExtendThread();
        ExtendThread extendThread3 = new ExtendThread();
//        executorService.execute(extendThread1);
//        executorService.execute(extendThread2);
//        executorService.execute(extendThread3);
        Future<?> submit = executorService.submit(extendThread1);
        Future<?> submit2 = executorService.submit(extendThread2);
        Future<?> submit3 = executorService.submit(extendThread3);
//        extendThread1.start();
//        extendThread1.join();
//        extendThread2.start();
//        extendThread2.join();
//        extendThread3.start();
//        extendThread3.join();
        executorService.shutdown();
    }
}
