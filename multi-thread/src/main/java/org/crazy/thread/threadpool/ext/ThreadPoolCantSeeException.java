package org.crazy.thread.threadpool.ext;

import java.util.concurrent.*;

/**
 * @author chenyao
 * @date 2019/6/eight 16:08
 * @description 在线程池的使用中 很有可能发生异常 但是看不到任何日志信息
 */
public class ThreadPoolCantSeeException extends ThreadPoolExecutor {

    public ThreadPoolCantSeeException(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(wrap(command));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task));
    }

    public Runnable wrap(Runnable task) {
        return () -> {
            try {
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        };
    }

    static class TaskJob extends Thread {
        int a, b;

        public TaskJob(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            System.out.println(a / b);
            /**
             * 改进方法3 run内部使用try catch 不足之处是每个线程都要这么做 方法2 则一劳永逸
             */
           /* try {
                System.out.println(a / b);
            } catch (Exception e) {
                e.printStackTrace();
            }*/

        }
    }

    public static void main(String[] args) {
        method1();
//       method2();

    }

    private static void method2() {
        /**
         * 改进方法2 扩展线程池
         */
        ThreadPoolCantSeeException pool = new ThreadPoolCantSeeException(4, 4, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100));
        for (int i = 0; i < 5; i++) {
            pool.submit(new TaskJob(i + 1, i));
        }
        pool.shutdown();
    }

    private static void method1() {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            //只打印了4个结果 没有异常堆栈信息
            pool.submit(new TaskJob(i + 1, i));
            /**
             * 改进方法1 使用execute 会抛出异常
             */
//            pool.execute(new TaskJob(i+1,i) );
        }
        pool.shutdown();
    }

}
