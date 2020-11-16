package org.crazy.thread.base.theadlocal;

import java.util.HashSet;
import java.util.Set;

/**
 * ThreadLocal 的研究测试
 *
 * 研究表明：
 *      final修饰的field是不能修改的
 *      但如果修饰的field是可变的(引用变量)，那么这些被引用的对象是可以修改的
 */
// TODO: 2018/5/27  该对象用于--防止--【可变的】单实例对象或者全局变量的共享
public class ThreadLocalTest {

    // TODO: 2018/5/27  ThreadLocal验证
    private static ThreadLocal<Integer> globalInt = new ThreadLocal<Integer>();

    // TODO: 2018/5/27 验证 final类型的引用变量是否可以改变
    private final static Set<Integer> set =new HashSet<Integer>();
    private final static String str = "chenyao";

    /**
     *  验证 对于ThreadLocal类保存的变量 ，在不同的线程是否是复制该变量到新的线程，
     *  从而保证每个线程get到的变量都是新的拷贝，让每个线程都有属于自己的变量
     */
    public static void main(String[] args) {

        // TODO: 2018/5/27 ThreadLocal验证
        System.out.println("初始化的值："+globalInt.get());
        globalInt.set(10);
        System.out.println("+10后的值："+globalInt.get());

        //新的线程在get获取值的时候，仍然是默认值，并不受主线程的set影响
        //查看get的方法，即可看到通过getMap()方法判断当前线程是否已经调用过get 如果第一次则初始化value值
        new Thread(() -> System.out.println("新线程的值："+globalInt.get())).start();


        // TODO: 2018/5/27 final 验证
        /**
         * 事实表明 即便是final类型，如果是引用变量，那么引用变量仍然可以改变，只是引用必须保持不变而已
         *  也就是说，对于引用变量而言，final修饰的话，只要引用保持不变，该变量可以任意修改其内部的值
         */
        set.add(1);
        set.add(2);
        set.add(3);
        System.out.println(set);
        //此处会有编译异常,因为set是final修饰的，引用不能改变
        //set=new HashSet<Integer>();
        //对于非引用型变量,final修饰的话，无法改变其值，编译报错
        //str="zhang";

    }

}
