package org.crazy.thread.base;

/**
 * @author chenyao
 * @date 2019/5/16 eight:30
 * @description 线程生命周期探索
 */
public class ThreadLife implements Runnable {

    private int cnt = 0;

    public static void main(String[] args) throws InterruptedException {

        //1.线程创建
        Thread thread = new Thread(new ThreadLife());
        //2.可运行状态
        thread.start();
        //3.t
        thread.join(1000);
        //4
        thread.wait(1000);
        thread.notify();
        thread.notifyAll();
    }

    @Override
    public void run() {
        //3.抢占得到CPU时间分片 正在运行
        for (int j = 0; j < 100; j++) {
            cnt++;
        }
        notify();
        notifyAll();
        Thread.yield();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cnt);
    }
}
