package com.study.jdk.seven;

import org.junit.Test;

/**
 * @author chenyao
 * @date 2020/3/31 12:47
 * @description 二分查找
 */
public class TwoSplitFind {

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            System.out.println(m(i));
        }


    }


    public static int m(int k) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int half;
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            half = (i + j) / 2;
            int v = arr[half];
            if (v > k) {
                j = half - 1;
            } else if (v < k) {
                i = half + 1;
            } else {
                return half;
            }
        }
        return -1;
    }

    @Test
    public void n() {
        int k = 4;
        int[] arr = {1, 2, 5, 5, 5, 9, 12, 16, 111, 123, 11111};
        int half;
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            half = (i + j) / 2;
            int v = arr[half];
            if (v > k) {
                j = half - 1;
            } else if (v <= k) {
                i = half + 1;
            }
        }
        System.out.println(arr[i]+"--"+i);
    }

    @Test
    public void t(){
        /**
         * 最大连续子序列之和
         */
        int[] arr = { 1, -3, 7, 8, -4, 12, -10, 6 };

        for (int n : arr) {

        }

    }
}


