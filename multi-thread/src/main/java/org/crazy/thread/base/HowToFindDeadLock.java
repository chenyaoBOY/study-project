package org.crazy.thread.base;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author chenyao
 * @date 2019/10/25 10:33
 * @description 使用诊断工具 查找死锁
 *
 * 1 使用Jps命令 找出程序的进程id
 * 2 使用jstack命令 查看进程信息
 *
 *  Found one Java-level deadlock:
 * =============================
 * "Thread-1":
 *   waiting to lock monitor 0x00000000178e99f8 (object 0x00000000d6259d98, a java.lang.Object),
 *   which is held by "Thread-0"
 * "Thread-0":
 *   waiting to lock monitor 0x00000000178eda98 (object 0x00000000d6259da8, a java.lang.Object),
 *   which is held by "Thread-1"
 */
public class HowToFindDeadLock  extends Thread{

    private Object o1;
    private Object o2;

    HowToFindDeadLock(Object o1,Object o2){
        this.o1=o1;
        this.o2=o2;
    }
    @Override
    public void run() {
        synchronized (o1){
            System.out.println("成功获取锁lockA="+o1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o2){
                System.out.println("成功获取锁lockB="+o2);
            }

        }
    }

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        HowToFindDeadLock lock1 = new HowToFindDeadLock(o1,o2);
        HowToFindDeadLock lock2 = new HowToFindDeadLock(o2,o1);
        lock1.start();
        lock2.start();


        /**
         * 通过 ThreadMXBean 自带的 findDeadlockedThreads 函数可以找到死锁线程
         * 但是要注意的是，对线程进行快照本身是一个相对重量
         * 级的操作，还是要慎重选择频度和时机
         */
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
                if(deadlockedThreads != null){
                    ThreadInfo[] threadInfo = threadMXBean.getThreadInfo(deadlockedThreads);
                    for (ThreadInfo t : threadInfo) {
                        System.out.println(t.getThreadName()+" 线程发生了死锁！");
                    }
                }
            }
        };

        ScheduledExecutorService ex = Executors.newScheduledThreadPool(1);

        ex.scheduleAtFixedRate(runnable,5L,10L, TimeUnit.SECONDS);
    }

}
