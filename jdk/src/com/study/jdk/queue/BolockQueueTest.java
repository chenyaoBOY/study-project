package com.study.jdk.queue;

/**
 * @author chenyao
 * @Description:
 * @date 2018/6/29/17:48
 */
public class BolockQueueTest {

    private static final Student student = new Student();

    static ThreadLocal<Student> threadLocal = ThreadLocal.withInitial(() -> new Student());

    public static void main(String[] args) {

        class demo{

        }

        System.out.println(getStudent());

        float a = 1111111111111111120.00f;
        int b=(int)a;
        System.out.println(b);
        student.setName("陈瑶");
        for (int i = 0; i < 5; i++) {
            new Runnable() {
                @Override
                public void run() {
                    System.out.println(getStudent());
                    System.out.println(getStudent().getName());
                }
            }.run();
        }

    }


    public static  Student getStudent(){
        threadLocal.set(new Student());
        return threadLocal.get();
    }
}
