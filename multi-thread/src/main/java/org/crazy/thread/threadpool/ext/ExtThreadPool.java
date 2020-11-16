package org.crazy.thread.threadpool.ext;

import java.util.concurrent.*;

/**
 * @author chenyao
 * @date 2019/6/eight 14:52
 * @description 线程池扩展 线程池在执行线程任务时提供了扩展
 * beforeExecute\afterExecute\terminated
 * 在线程执行任务的前后 添加了模板方法 可供自定义扩展
 */
public class ExtThreadPool extends ThreadPoolExecutor {

    public ExtThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    static class TaskJob extends Thread {
        String name;

        public TaskJob(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"执行任务ing");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("beforeExecute线程名称=" + t.getName() + " 准备执行" + ((TaskJob) r).name);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("afterExecute执行完毕" + ((TaskJob) r).name);
    }

    @Override
    protected void terminated() {
        System.out.println("线程池关闭");
    }


    public static void main(String[] args) {
        ExtThreadPool threadPool = new ExtThreadPool(4, 4, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

        for (int i = 0; i < 1000; i++) {
            threadPool.execute(new TaskJob("任务序号"+i));
        }
        threadPool.shutdown();
    }
}
