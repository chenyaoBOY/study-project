package leetcode.t30;

import java.util.*;

/**
 * @author chenyao
 * @date 2019/9/4 10:13
 * @description 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，
 * 并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 数组中找两个数相加等于零001 {
    /**
     * @param nums:
     * @param target:
     * @return int[]
     * @description //TODO 一遍hash
     * @author chenyao
     * @date 2019/9/4 10:32
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                result[0] = map.get(nums[i]);
                result[1] = i;
            }
            map.put(target - nums[i], i);
        }
        return result;
    }

    /**
     * 数组遍历查找
     * 暴力破解
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(i, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            int two = target - nums[i];
            if (map.containsValue(two)) {
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (entry.getKey() != i && entry.getValue() == two) {
                        return new int[]{i, entry.getKey()};
                    }
                }
            }
        }
        throw new IllegalArgumentException();
    }


}
