package com.study.jdk.algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author chenyao
 * @date 2019/12/10 14:40
 * @description 九大经典排序算法
 */
public class NineClassicSortAlgorithmTest {

    private static int[] arr = {18, 2, 3, 5, 4, 6, 8, 9, 10};
    public static int n = arr.length;

    static {
        System.out.println("元数据：" + Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     * 基本思想：相邻数字比较，较大数字后移，这样每趟比较之后 最大值必然在最后的位置
     * 第一趟至少要比较 n-1 次
     * 最后一趟至少比较 1 次
     * 需要比较n-1 趟
     * 总次数 = (n-1)(n-1+1)/2= n的平方除以2  时间复杂度  O(n2)
     */
    @Test
    public void maoPao() {
        for (int i = 0; i < n - 1; i++) {//需要比较n-1趟
            for (int j = 0; j < n - 1; j++) {//数组最大下标为 n-1 由于j+1的存在 这里j最大只能是n-2
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            System.out.println("第" + i + "趟结束：" + Arrays.toString(arr));
        }
    }

    /**
     * 冒泡算法优化：
     * 1 由于第i趟 比较以后 最大值的排序已经不需要再去排序了 所以没必要在比较
     * 2 如果没有发生过数字交换  说明排序已经成功
     */
    @Test
    public void maoPaoBest() {
        for (int i = 0; i < n - 1; i++) {//需要比较n-1趟
            boolean flag = true;
            /**
             * 第一趟需要比较到n-1下标位置
             * 第二趟需要比较到n-2的位置即可 第二趟开始时 N-1的位置已经是最大值 无需比较
             */
            for (int j = 0; j < n - 1 - i; j++) {//数组最大下标为 n-1
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag = false;
                }
            }
            System.out.println("第" + i + "趟结束：" + Arrays.toString(arr));
            if (flag) {//经过本趟比较 发现相邻数字总是右边值大 那么排序已经可以提前结束了
                break;
            }
        }
    }

    /**
     * 选择排序：
     * 在长度为N的无序数组中，第一次遍历n-1个数，找到最小的数值与第一个元素交换；
     * 第二次遍历n-2个数，找到最小的数值与第二个元素交换；
     * 。。。
     * 第n-1次遍历，找到最小的数值与第n-1个元素交换，排序完成。
     * <p>
     * 需要遍历的趟数为:n-1
     * 第一趟比较 n-1次
     * 第二趟较上次少一次
     * 最后一次 比较一次    时间复杂度O(n2)
     */
    @Test
    public void select() {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
            System.out.println("第" + i + "趟结束：" + Arrays.toString(arr));
        }

    }

    /**
     * 插入排序:
     * 将数字插入已经排序的数组中
     * 1 一个数字时，代表数组中下标为0的数字arr[0] 已经排序了
     * 2 将第二个数字插入arr[0] 只需要比较 arr[1]>?arr[0] 大于不用交换 arr[0] arr[1]就是一个有序数组了
     * 3 第三个数字 插入到arr[0] arr[1]中 需要先比较arr[2]>?arr[1] 否：结束  是：交换 并比较arr[1] arr[0] 重复步骤2
     * 以此类推
     * <p>
     * 需要n-1趟插入
     * 第一趟插入至少一次
     * 第n-1趟插入 至多需要比较 n-1次 时间复杂度 O(n2)
     * <p>
     * 使用场景：
     * 小规模数据
     * 数据基本有序的情况下 非常高效
     */
    @Test
    public void insert() {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int t = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = t;
                } else {
                    break;
                }
            }
            System.out.println("第" + i + "趟结束：" + Arrays.toString(arr));
        }
    }

    /**
     * 希尔排序：
     * 使用插入排序的方式将一个无序数组 局部排序 使 整体大致有序  最终在使用一次全部的插入排序排序完成
     * 所谓局部排序就是 分段排序
     * 1 排序arr[0] arr[4] arr[8]
     * 2 排序arr[1] arr[5]
     * arr[2] arr[6]  arr[3] arr[7]
     * 以此类推
     * 然后排序 arr[0] arr[2] arr[4]
     * arr[1] arr[3] arr[5]
     * 最后进行全部的插入排序
     * {18, 2, 3, 5, 4, 6, 8, 9, 10}
     */
    @Test
    public void xier() {
        for (int skip = n / 2; skip > 0; skip /= 2) {//skip为分段的值 可以采用不同的策略 这里就简单使用 n/2的策略

            for (int i = 0; i < skip; i++) {//本次循环 目的是为了错开起始下标 让每个元素都能遍历到

                for (int j = i; j < n - skip; j += skip) { //这个是为了遍历数组 跳跃的方式遍历  i=0时0 4 8  i=1时 1 5 9

                    for (int k = j + skip; k > skip - 1; k -= skip) {//采用插入排序 进行排序
                        if (arr[k] < arr[k - skip]) {
                            int t = arr[k];
                            arr[k] = arr[k - skip];
                            arr[k - skip] = t;
                        } else {
                            break;
                        }
                    }
                    System.out.println("第" + i + "趟结束：" + Arrays.toString(arr));
                }

            }
        }

    }


    /**
     * 快速排序
     * 72 - 6 - 57 - 88 - 60 - 42 - 83 - 73 - 48 - 85
     * 洞 - 6 - 57 - 88 - 60 - 42 - 83 - 73 - 48 - 85
     * 将arr[0] 挖出 放入key中保存   空出一个位置 现在就需要填洞  i=0,j=9
     * 从右边找出小于key的值 48 将其填入空洞arr[0]  j=8
     * <p>
     * 48 - 6 - 57 - 88 - 60 - 42 - 83 - 73 - 洞  - 85
     * <p>
     * 此时arr[8]位置空了
     * 从左边找出大于key的值  arr[3]=88 填入  i=3 j=8
     * <p>
     * 48 - 6 - 57 -  洞   - 60 - 42 - 83 - 73 - 88  - 85
     * <p>
     * 此时arr[3]空了
     * 找到arr[5]=42 小于72 填入 j=5 i=3
     * 48 - 6 - 57 -42 - 60 -  洞   - 83 - 73 - 88  - 85
     * <p>
     * i继续向前 发现当I=5时已经没有比72的值大了 且 i=j 此时将72放入该洞
     * 48 - 6 - 57 -42 - 60 -  72- 83 - 73 - 88  - 85
     * 这样72 左边的都小于它  右边的都大于它
     */
    @Test
    public void quickSort() {
//        arr = new int[]{72 , 6 , 57 , 88 , 60 , 42 , 83 , 73 , 48 , 85};
//        n=arr.length;
//        System.out.println( Arrays.toString(arr));
        quickSort(0, n - 1);
        System.out.println("排序完毕：" + Arrays.toString(arr));
    }

    private void quickSort(int i, int j) {
        if (i >= j) {
            return;
        }
        int left = i, right = j;
        int location = i;//记录洞的位置
        int key = arr[i]; //将首位取出 则arr[i]空出一个洞来

        while (i < j) {
            while (j > location) {//从[i,j] 数组分段的右边 开始找出小于key的值 放入洞中
                if (arr[j] < key) {
                    arr[location] = arr[j];
                    location = j;//标记洞的位置
                    break;
                }
                j--;
            }
            while (i < location) {//从[i,j] 数组分段的左边 开始找出大于key的值 放入洞中
                if (arr[i] > key) {
                    arr[location] = arr[i];
                    location = i;//标记洞的位置
                    break;
                }
                i++;
            }
        }
        if (j == i) {
            arr[location] = key;
            quickSort(left, location - 1);
            quickSort(location + 1, right);
        }

    }

    /**
     * 归并排序 未完成？
     */
    @Test
    public void guibing() {
        for (int cnt = 2; cnt < n; cnt += 2) {//合并的元素个数 从 2开始 最大的个数应该是 数组长度的一半

            for (int i = 0; i <= n - 1 - cnt; i += cnt) {//cnt=2时 i= 0 2 4  cnt=4时 i=0 4 8

                int mid = cnt / 2;//归并数组的中间位置 cnt=2 mid=1 cnt=4 mid=2 左边以mid为上限 不可等于 右边下限 需要等于
                int[] tmp = new int[cnt];
                int index1 = i;//index1 标记左边数组的移动下标的位置
                int index2 = mid + i;// index2 记录右边数组的下标的位置
                int t = 0;
                //针对 arr[i]-arr[i+cnt]的合并
                while (index1 < mid + i && index2 < cnt + i) {
                    if (arr[index1] < arr[index2]) {
                        tmp[t++] = arr[index1++];
                    } else {
                        tmp[t++] = arr[index2++];
                    }
                }
                while (index1 < mid + i) {
                    tmp[t++] = arr[index1++];
                }
                while (index2 < cnt + i) {
                    tmp[t++] = arr[index2++];
                }
                //替换数据
                for (int j = 0; j < cnt; j++) {
                    arr[i + j] = tmp[j];
                }

            }
            System.out.println("第" + cnt + "趟结束：" + Arrays.toString(arr));
        }

    }

}
