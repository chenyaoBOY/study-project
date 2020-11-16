package org.study.project.jdbc;

import java.util.Random;

/**
 * @author chenyao
 * @date 2019/8/13 15:42
 * @description
 */
public class DD {
    public static void main(String[] args) {
        Random rnd = new Random();
        boolean toBe = rnd.nextBoolean();
        Number result = (toBe || !toBe) ? 3 : 1.0;
        System.out.println(result);
        Number result2 =new Integer(3);
        System.out.println(result2);
    }
}
