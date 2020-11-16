package org.crazy.thread.base;

/**
 * @author chenyao
 * @date 2019/6/17 15:37
 * @description  将一类线程分组管理
 */
public class ThreadGroupTest implements Runnable{

    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("线程组");
        new Thread(group, new ThreadGroupTest(), "t1").start();
        new Thread(group, new ThreadGroupTest(), "t2").start();

    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getThreadGroup().getName()+"-"+Thread.currentThread().getName());
    }
}
