package org.crazy.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyao
 * @date 2020/1/21 15:01
 * @description
 */
public class ReferenceTest {
    private  int age =18;
    public static void main(String[] args) {

        ReferenceTest t1 = new ReferenceTest();
        ReferenceTest t2= t1;
        t1 = null;
        System.out.println(t2.age);

    }
}
