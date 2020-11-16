package org.crazy.thread.base;

/**
 * @author chenyao
 * @date 2019/6/17 14:17
 * @description suspend线程挂起和resume线程挂起恢复
 *      方法已经被舍弃
 *      若resume方法在suspend方法前调用，则该线程可能永远无法退出
 */
public class SuspendAndResume {

    static Object obj = new Object();
    static Thread1 t1 = new Thread1("t1");
    static Thread1 t2 = new Thread1("t2");

    static  class Thread1 extends Thread{
        public Thread1(String name) {
            super(name);
        }
        @Override
        public void run() {
            synchronized (obj){
                System.out.println("线程"+Thread.currentThread().getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
        t2.resume();//这里t2线程开启和恢复挂起之间没有时间间隔，由于start之后并不会立马执行，所以resume可能会比run方法先执行，如果在前面加上等待时间，就可以了
        t1.join();
        t2.join();


    }
}
