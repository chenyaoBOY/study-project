package org.study.project.jdk;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: chenyao
 * Date: 2019/1/10 14:06
 * Description: 模拟内存溢出
 */
public class OOM {



    public static void main(String[] args) {
//        heapOOM();
        /*try {
            stackOOM();
        } catch (Throwable e) {
            System.out.println("============num:"+num);
            throw e;
        }*/
//        methodAreaOOM();

        testGCAllocation();
    }
    /**
     * VM参数设置 -Xms20M -Xmm20M
     */
//    public static void heapOOM(){
//        List list = new ArrayList();
//        while (true){
//            list.add(new Object());
//        }
//    }
    /**
     * VM参数设置  -Xss128k
     */
//    public static int num=0;
//    public static void stackOOM(){
//        num++;
//        stackOOM();
//    }
    /**
     * VM参数设置 -XX:PermSize=10M -XX:MaxPermSize=10M
     */
//    public static void methodAreaOOM(){
//        List list = new ArrayList();
//        int i=0;
//        while (true){
//            list.add(String.valueOf(i++).intern());
//        }
//    }

    public static final int _1MB=1024*1024;
    public static void testGCAllocation(){
        byte[] a = new byte[2*_1MB];
        byte[] b = new byte[2*_1MB];
//        byte[] c = new byte[2*_1MB];
//        byte[] d = new byte[4*_1MB];
    }


}
