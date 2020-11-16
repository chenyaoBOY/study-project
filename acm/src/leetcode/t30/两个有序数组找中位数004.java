package leetcode.t30;


import org.junit.Test;

/**
 * @author chenyao
 * @date 2019/9/10 10:45
 * @description 给定两个有序数组  寻找中位数
 * 要求时间复杂度：O(log(m + n))
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class 两个有序数组找中位数004 {
    /**
     * 思路：
     * 1两个有序数组合成一个有序数组
     * 2计算中位数即可
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int i = 0, j = 0, k = 0, n = nums1.length, m = nums2.length;
        int[] arr = new int[n + m];

        //时间复杂度  O(m+n) 不满足条件 O(log(m + n)) 需要用二分法
        while (i < n || j < m) {
            if (i != n && j != m) {
                if (nums1[i] <= nums2[j]) {
                    arr[k] = nums1[i++];
                } else {
                    arr[k] = nums2[j++];
                }
            } else if (i == n) {
                arr[k] = nums2[j++];
            } else if (j == m) {
                arr[k] = nums1[i++];
            }
            k++;
        }
        if (arr.length % 2 == 0) {
            return (arr[arr.length / 2 -1] + arr[arr.length / 2 ]) / 2.0;
        } else {
            return arr[arr.length / 2 ];
        }
    }

    /**
     * 二分递归法
     *
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;
        if(m>n){
            int temp=n;     n=m;         m=temp;
            int[] t =nums1; nums1=nums2; nums2=t;
        }
        int half = m/2,i,j;

        //i+j=m−i+n−j+1   i= [0,m] j =(m+n+1)/2 - i
        while (true){
            i = half;j=(m+n+1)/2 - i;
            if(i == 0){ //i=0

            }
        }

    }

        @Test
    public void test(){
        int[] arr1 = {1,2,3,4,8,9};
        int[] arr2 = {1,2,3,4,5,6};
        System.out.println(findMedianSortedArrays(arr1,arr2));
    }
}
