package leetcode.t30;


import java.util.*;

/**
 * @author chenyao
 * @date 2019/9/4 15:04
 * @description
 *      * 给定一个包含 n 个整数的数组 nums，
 *      * 判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 *      * 找出所有满足条件且不重复的三元组。
 *      * <p>
 *      * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *      * 满足要求的三元组集合为：
 *      * [
 *      * [-1, 0, 1],
 *      * [-1, -1, 2]
 *      * ]
 */
public class 数组找三个数相加等于零015 {

    /**
     * 不考虑耗时 仅实现功能
     *      先找出符合 a+b+c=0的数据
     *      去重
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        list.add(new Integer[]{nums[i], nums[j], nums[k]});
                    }
                }
            }
        }
        Map<String, Integer> map = new HashMap<>(list.size());
        for (Integer[] arr : list) {
            Arrays.sort(arr);
            String key = "" + arr[0] + arr[1] + arr[2];
            if (!map.containsKey(key)) {
                map.put(key, 1);
                res.add(Arrays.asList(arr));
            }
        }
        return res;
    }

    /**
     * 参照TwoSum中的哈希表方式
     * 复杂度比上面的还大
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer[], Integer> map = new HashMap<>(nums.length * 2);
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                map.put(new Integer[]{nums[i], nums[j], i, j}, nums[i] + nums[j]);
            }
        }
        Set<String> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsValue(-nums[i])) {
                for (Map.Entry<Integer[], Integer> entry : map.entrySet()) {
                    Integer[] arr = entry.getKey();
                    Integer[] arr2 = new Integer[]{nums[i], arr[0], arr[1]};
                    Arrays.sort(arr2);
                    String key = "" + arr2[0] + arr2[1] + arr2[2];
                    if (entry.getValue() == -nums[i] && i != arr[2] && i != arr[3] && !set.contains(key)) {
                        res.add(Arrays.asList(arr2));
                        set.add(key);
                    }
                }

            }
        }
        return res;
    }

    /**
     * 使用算法思想
     *      1 先排序
     *      2 选取起始值 nums[i] i=0 循环开始
     *      3 选取最靠近nums[i]的值 nums[i+1] 和最原理它的值nums[length-1] 进行求和sum
     *      4 sum=0 符合条件 i++
     *      5 sum<0  最大值不够大 需要将nums[L]的值增大 L++
     *      6 sum>0  最大值过大  需要将nums[R]的值减小  R--
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            if(i>0 && nums[i] == nums[i-1]) continue;
            int L = i + 1;
            int R = nums.length - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while(L<R && nums[L+1] == nums[L] ) L++;
                    while(L<R && nums[R-1] == nums[R] ) R--;
                    L++;
                    R--;
                } else if (sum < 0) { //大值不够大 arr[L]不够小
                    L++;
                } else if (sum > 0) {
                    R--;
                }
            }
        }
        return res;
    }


    public static List<List<Integer>> threeSum4(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            if(i>0 && nums[i] == nums[i-1]) continue;
            int L = i + 1;
            int R = nums.length - 1;
            Integer preL = null;
            Integer lastR = null;
            while (L < R) {
                if (preL != null && nums[L] == preL) {
                    L++;
                    continue;
                }
                if (lastR != null && nums[R] == lastR) {
                    R--;
                    continue;
                }
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    preL = nums[L];
                    lastR = nums[R];
                    L++;
                    R--;
                } else if (sum < 0) { //大值不够大 arr[L]不够小
                    preL = nums[L];
                    L++;
                } else if (sum > 0) {
                    lastR = nums[R];
                    R--;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};

//        System.out.println(JSONObject.toJSONString(threeSum3(nums)));
        Demo demo = new Demo();
//        System.out.println(JSON.toJSONString(demo));
//        System.out.println(JSONObject.toJSONString(demo));
    }


    static class Demo{
        int age;
        String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
