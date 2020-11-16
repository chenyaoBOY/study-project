package org.crazy.thread.advanced.atomic;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author chenyao
 * @date 2019/6/21 11:03
 * @description
 */
public class AtomicReferenceTest {
    static AtomicReference<Integer> money = new AtomicReference<>();
    static AtomicStampedReference<Integer> price = new AtomicStampedReference<>(19, 0);

    public static void main(String[] args) throws InterruptedException {
//       testAtomicReference();

//        question4ABA();
        noABA();


    }

    /**
     * @param
     * @return void
     * @description //TODO 解决ABA的问题就是添加额外的状态 AtomicStampedReference可以简单理解为添加了一个时间戳
     * @author chenyao
     * @date 2019/6/21 15:42
     */
    private static void noABA() throws InterruptedException {
        CountDownLatch count = new CountDownLatch(100);
        CountDownLatch door = new CountDownLatch(1);
        /**
         * CAS的ABA问题的解决
         *      当total = 19时，此时充值线程将数值加20，19+20=39，此时还有其他充值线程T1在进行充值也就是比较交换
         *      与此同时，消费线程将39消费到了19，那么T1在比较交换的时候发现期望值是对的，但是AtomicStampedReference
         *      中加入了薪的状态，消费线程将状态stamp+1 ，而T1此时的stamp还是原来的值，更新的时候发现stamp的状态不一致，
         *      则此次更新失败
         */
        new Thread(() -> {
            while (true) {
                Integer stamp = price.getStamp();
                Integer total = price.getReference();
                if (total > 20) {
                    //
                    if (price.compareAndSet(total, total - 20, stamp, stamp + 1)) {
                        System.out.println("消费成功");
                    }
                }
            }
        }).start();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Integer stamp = price.getStamp();
                Integer total = price.getReference();
                if (total < 20) {
                    count.countDown();
                    try {
                        door.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (price.compareAndSet(total, total + 20, stamp, stamp + 1)) {
                        System.out.println("充值成功");
                    }
                }
            }).start();
        }

        count.await();//等待计数器清零
        door.countDown();//释放闸门
    }

    private static void question4ABA() throws InterruptedException {
        /**
         * CAS的ABA问题
         *      当total = 19时，此时充值线程将数值加20，19+20=39，此时还有其他充值线程在进行充值也就是比较交换
         *      与此同时，消费线程将39消费到了19，那么其他充值线程在比较交换的时候发现期望值是对的，从而继续充值+20.
         */
        money.set(19);
        CountDownLatch count = new CountDownLatch(100);
        CountDownLatch door = new CountDownLatch(1);
        new Thread(() -> {
            while (true) {
                Integer total = money.get();
                if (total > 20) {//只要金额大于20 立即消费，试图让充值线程产生ABA问题
                    if (money.compareAndSet(total, total - 20)) {
                        System.out.println("消费成功");
                    }
                }
            }
        }).start();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Integer total = money.get();
                if (total < 20) {
                    count.countDown();
                    try {
                        door.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //启动50个线程 同事去做比较交换的操作 同时消费线程将数据修改为原状态 产生ABA问题
                    if (money.compareAndSet(total, total + 20)) {
                        System.out.println("充值成功");
                    }
                }
            }).start();
        }

        count.await();//等待计数器清零
        door.countDown();//释放闸门
    }


    private static void testAtomicReference() throws InterruptedException {
        CountDownLatch letGoDoor = new CountDownLatch(1);
        CountDownLatch countDoor = new CountDownLatch(50);
        money.set(19);
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                while (true) {//不断扫描余额小于20的卡 自动充值
                    Integer total = money.get();
                    if (total < 20) {
                        try {
                            countDoor.countDown();
                            letGoDoor.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        /**
                         * 第一个参数是total是期望值，比较交换的时候，jvm获取到money的最新值去和total比较
                         * 若一致则更新total+20 比较和赋值这是原子操作
                         */
                        if (money.compareAndSet(total, total + 20)) {
                            System.out.println("余额" + money.get() + " 充值成功" + " 原余额" + total);
                            break;
                        } else {
                            System.out.println("比较交换失败！");
                            break;
                        }
                    } else {
                        System.out.println("余额" + total + "大于20元");
                        break;
                    }
                }
            }).start();
        }
        countDoor.await();
        letGoDoor.countDown();
    }


    int num = 0;

    @Test
    public void test() {
        AtomicReference<Integer> re = new AtomicReference<>();
        re.set(num);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
//                    num++;
//                    re.set(num);
                    for (; ; ) {
                        Integer read = re.get();
                        if (re.compareAndSet(read, read + 1)) {
                            break;
                        }
                    }
                }
            }).start();
        }
        while (Thread.activeCount() > 1) {

        }
        System.out.println(re.get());
    }
}
