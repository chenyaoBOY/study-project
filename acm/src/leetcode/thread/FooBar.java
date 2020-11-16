package leetcode.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author chenyao
 * @date 2019/9/5 11:27
 * @description
 */
public class FooBar {
    private int n;
    private Object obj = new Object();
    private CountDownLatch door = new CountDownLatch(1);
    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (obj){
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                if(door.getCount() == 1){
                    door.countDown();
                }
                obj.notify();
                obj.wait();
            }
        }
    }

    public  void bar(Runnable printBar) throws InterruptedException {
        door.await();
        for (int i = 0; i < n; i++) {
            synchronized (obj){
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                obj.notify();
                if(i < 9){
                    obj.wait();
                }
            }
        }
    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(10);
        new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.println("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
