package org.crazy.thread.threadpool;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenyao
 * @Description: 线程池的真正实现类
 * @date 2018/6/4/17:24
 */
public class ThreadPoolExeutorTest {

    static class MyThreadFactory implements ThreadFactory{
        private static AtomicInteger count = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "自定义线程"+count.getAndIncrement());
            return thread;
        }
    }

    private static final CountDownLatch start = new CountDownLatch(1);

    private static final Lock lock = new ReentrantLock();

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR =  new ThreadPoolExecutor(3,3,0,
            TimeUnit.SECONDS,new ArrayBlockingQueue<>(20),new MyThreadFactory());

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            THREAD_POOL_EXECUTOR.execute(()->{
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        THREAD_POOL_EXECUTOR.shutdown();
//        test01();

 /*       for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
//                    start.await();
//                    lock.lock();
                    boolean b = lock.tryLock();
                    if(b){
                        try {
                            System.out.println("线程"+Thread.currentThread().getName()+"已加锁 trylock="+b);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if(Thread.currentThread().getName().equals("Thread-12")){
                                lock.unlock();
                            }else{
                                System.out.println("线程"+Thread.currentThread().getName()+"释放锁");
                            }
                        }
                    }else{
                        System.out.println("锁被占用");
                    }
//                    System.out.println("线程"+Thread.currentThread().getName()+"已加锁 trylock=");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//                    lock.unlock();
//                    System.out.println("线程"+Thread.currentThread().getName()+"释放了锁");
                }

            }).start();
        }
*/
        Thread.sleep(2000);
        start.countDown();

    }

    @Test
    public static synchronized void test01(){


        boolean b = Thread.holdsLock(ThreadPoolExeutorTest.class);
        System.out.println(b);

    }
}
