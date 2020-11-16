package leetcode.thread;


import java.util.concurrent.CountDownLatch;

/**
 * @author chenyao
 * @date 2019/9/5 10:39
 * @description
 */
public class Foo {

     CountDownLatch door = new CountDownLatch(1);
     CountDownLatch door2 = new CountDownLatch(1);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        door.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        door.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        door2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        door2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
