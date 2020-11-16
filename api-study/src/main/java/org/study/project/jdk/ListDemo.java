package org.study.project.jdk;

import java.util.Arrays;
import java.util.List;

/**
 * @author chenyao
 * @date 2020/3/19 10:21
 * @description
 */
public class ListDemo {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);

        integers.add(5);
        System.out.println(integers);


    }
}
