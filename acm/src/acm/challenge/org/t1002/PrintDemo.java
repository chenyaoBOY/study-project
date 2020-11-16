package acm.challenge.org.t1002;

import java.util.concurrent.CountDownLatch;

/**
 * @author chenyao
 * @date 2020/3/16 17:02
 * @description
 */
public class PrintDemo {
    private static Object obj = new Object();
    private static char[] lower = new char[26];
    private static char[] upper = new char[26];
    private static CountDownLatch door = new CountDownLatch(1);
    private static CountDownLatch totalTimeControl = new CountDownLatch(2);
    static {
        int lowerCnt = (int) 'a';
        int upperCnt = (int) 'A';
        for (int i = 0; i < lower.length; i++) {
            lower[i] = (char) (lowerCnt++);
            upper[i] = (char) (upperCnt++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        new LowerThread().start();
        new UpperThread().start();
        totalTimeControl.await();
        long total = System.currentTimeMillis() -start;
        System.out.println("总耗时："+total+"ms");
    }

    static class LowerThread extends Thread {
        @Override
        public void run() {
            long start = System.currentTimeMillis();
            door.countDown();
            for (char c : lower) {
                synchronized (obj) {
                    System.out.print(c);
                    obj.notify();
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            long total = System.currentTimeMillis() -start;
            System.out.println("LowerThread线程耗时："+total+"ms");
            totalTimeControl.countDown();
        }
    }

    static class UpperThread extends Thread {
        @Override
        public void run() {
            long start = System.currentTimeMillis();
            try {
                door.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (char c : upper) {
                synchronized (obj) {
                    System.out.print(c);
                    obj.notify();
                    try {
                        if(c == 'Z'){
                            System.out.println();
                            break;
                        }
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            long total = System.currentTimeMillis() -start;
            System.out.println("UpperThread线程耗时："+total+"ms");
            totalTimeControl.countDown();
        }
    }
}
