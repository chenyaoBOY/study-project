package org.crazy.thread.validate;

/**
 * @author chenyao
 * @date 2020/1/9 14:41
 * @description
 */
public class ThreadVisbility {
    public static volatile boolean flag=true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (flag){

            }
            System.out.println("end");
        },"test-volatile").start();

        Thread.sleep(1000);

        flag=false;
    }
}
