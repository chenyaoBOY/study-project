package org.crazy.thread.advanced.atomic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author chenyao
 * @date 2019/6/21 11:03
 * @description
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            singlePrint();
        }

    }

    public static void singlePrint() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        CyclicBarrier barrier = new CyclicBarrier(2);
        CountDownLatch door = new CountDownLatch(2);
        for (int i = 1; i < 3; i++) {
            int n=i;
            new Thread(() -> {
                try {
                    barrier.await();
                } catch (BrokenBarrierException | InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(n);
                door.countDown();
            }).start();
        }
        door.await();
        System.out.println(list);
    }




}
