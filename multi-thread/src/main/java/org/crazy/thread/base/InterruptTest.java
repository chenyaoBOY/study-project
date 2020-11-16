package org.crazy.thread.base;

/**
 * @author chenyao
 * @date 2019/6/14 17:54
 * @description
 *        线程中断方法的基本使用
 *
 *        1.Thread.interrupt()方法并不能让线程自己中断，他只是给线程一个中断标记，\
 *              以让线程在运行的时候根据该标记判断线程是否应该终止
 *        2.当线程调用sleep方法时，会抛出Interrupt异常，当调用该线程的interrupt方法时，
 *        给线程一个中断标记，此时因为线程在睡眠触发了异常，那么进入cathch代码块，
 *        并清除了线程的中断标记，所以要想在此还想中断线程，就需要再调用一次Interfrupt方法
 */
public class InterruptTest {

    static Integer i =0;
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断");
                    break;
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    if(!Thread.currentThread().isInterrupted()){
                        System.out.println("线程睡眠中断 标记清除");
                        Thread.currentThread().interrupt();
                    }
                }
                i++;
            }
        });
        thread.start();
        Thread.sleep(2000);
        if(!thread.isInterrupted()){
            System.out.println("发送中断信息");
            thread.interrupt();
        }
        System.out.println(i);

    }
}
