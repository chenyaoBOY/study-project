package acm.challenge.org.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenyao
 * @date 2020/12/12 23:03
 * @description
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMaxSubStringUnique {
    /**
     * 执行结果： 通过 显示详情
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 27.73% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 20.49% 的用户
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0) return 0;
        char[] arr = s.toCharArray();
        int max = 0;
        //从下标0开始向右滑动 直到滑动到倒数第二个
        for(int i=0;i<arr.length;i++){
            int[] bucket = new int[200];
            boolean flag=false;
            //右边界从左下标开始移动 最大移动至 倒数第一个数
            for(int j=i;j<arr.length;j++){

                //判断集合里是否有重复值
                if(bucket[arr[j]] != 0){
                    max = (max > (j-i)?max :j-i);
                    flag=true;
                    break;
                }else{
                    bucket[arr[j]] = 1;
                }

            }
            if(!flag){
                max = (max > (arr.length-i)?max :(arr.length-i));
            }

        }
        return max;
    }

    /**
     * 执行用时：87 ms, 在所有 Java 提交中击败了17.76%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了52.48%的用户
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if(s==null || s.length()==0) return 0;
        char[] arr = s.toCharArray();
        int max = 0;
        Set<Character> set = new HashSet<>();
        //从下标0开始向右滑动 直到滑动到倒数第二个
        for(int i=0;i<arr.length;i++){
            boolean flag=false;
            //右边界从左下标开始移动 最大移动至 倒数第一个数
            for(int j=i;j<arr.length;j++){

                //判断集合里是否有重复值
                if(set.contains(arr[j])){
                    max = (max > (j-i)?max :j-i);
                    flag=true;
                    break;
                }else{
                    set.add(arr[j]);
                }

            }
            if(!flag){
                max = (max > (arr.length-i)?max :(arr.length-i));
            }
            set.clear();

        }
        return max;
    }
}
