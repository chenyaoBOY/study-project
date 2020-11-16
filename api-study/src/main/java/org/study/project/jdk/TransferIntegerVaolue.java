package org.study.project.jdk;

import java.lang.reflect.Field;

/**
 * @author chenyao
 * @date 2019/8/6 17:23
 * @description
 */
public class TransferIntegerVaolue {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        /**
         * Integer 在自动装箱时 调用的是Integer.valueof方法
         */
        Integer a = 1;// Integer a = Integer.valueOf(1);
        Integer b = 2;//Integer a = Integer.valueOf(1);
        System.out.println("交换前 a=" + a + " b=" + b);
        swap(a, b);
        System.out.println("交换后 a=" + a + " b=" + b);
    }

    /**
     * cache中的分布情况
     *下标  值
     * 0     -128
     * 1     -127
     * ...   ...
     * 128   0
     * 129   1
     * 130   2
     * ...   ...
     * 255   127
     *
     * set(1(129),Inteter.valueOf(2))
     *
     *下标   值
     * 0     -128
     * 1     -127
     * ...   ...
     * 128   0
     * 129   指向 130位置的 new Integer(2)
     * 130   2
     * ...   ...
     * 255   127
     *
     * set(2(129),Inteter.valueOf(1)) //此时 cache中下标 129和130 都指向的是同一个内存地址  2
     * 所以 Inteter.valueOf(1)的值仍然是2  故而需要new Integer(1)
     */
    public static void swap(Integer a, Integer b) throws NoSuchFieldException, IllegalAccessException {
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        int temp= a.intValue();
        field.set(a,b);
//        field.set(b,temp);//如果 a、b 的值大于128 则此处代码生效
        field.set(b,new Integer(temp));
    }
}
