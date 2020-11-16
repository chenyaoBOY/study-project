package org.crazy.thread.base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author chenyao
 * @date 2019/6/17 16:48
 * @description  不会爆出异常的错误问题
 */
public class MistakeWithMultiThread {


    @Test
    public void intOutValue(){
        /**
         * int 类型溢出问题
         */
        int a = Integer.MAX_VALUE;
        //结果=-2147483648 负数
        System.out.println("a+1="+(a+1));
    }
    @Test
    public void arrayList() throws InterruptedException {
        /**
         * ArrayList 并发下的问题
         *      1.ArrayIndexOutOfBoundsException: 540217
         *      2.无错误但结果不是200w 例如：1608562
         *      CopyOnWriteArrayList 使用线程安全的list即可
         */
        List<Integer> list = new ArrayList();
        Runnable run = () -> {
            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }
        };
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(list.size());
    }

    @Test
    public void hashMapMistake() throws InterruptedException {
        /**
         * 在jdk1.7的情况下
         * 可能会发生程序永远不退出的情况
         * 因为map可能发生了结构破坏，导致循环依赖
         */
        Map<String,String> map = new HashMap<>();
        Runnable run = () -> {
            for (int i = 0; i < 1000000; i++) {
                map.put(Integer.toString(i),Integer.toBinaryString(i));
            }
        };
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(map.size());
    }
}
