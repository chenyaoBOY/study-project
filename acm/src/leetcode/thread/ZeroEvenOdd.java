package leetcode.thread;

import java.util.concurrent.Semaphore;

/**
 * @author chenyao
 * @date 2019/9/5 13:44
 * @description
 */
public class ZeroEvenOdd {
    private int n;
    private Semaphore o = new Semaphore(0);
    private Semaphore e = new Semaphore(0);
    private Semaphore z = new Semaphore(1);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            z.acquire();
            printNumber.accpet(0);
            if (i % 2 == 0) {
                e.release();
            } else {
                o.release();
            }
        }


    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            e.acquire();
            printNumber.accpet(i);
            z.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            o.acquire();
            printNumber.accpet(i);
            z.release();
        }
    }

    static class IntConsumer {
        void accpet(int val) {
            System.out.print(val);
        }
    }

    public static void main(String[] args) {
        IntConsumer consumer = new IntConsumer();
        ZeroEvenOdd z = new ZeroEvenOdd(10);
        new Thread(() -> {
            try {
                z.zero(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                z.odd(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                z.even(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
