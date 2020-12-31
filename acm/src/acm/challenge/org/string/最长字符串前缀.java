package acm.challenge.org.string;

import java.util.Arrays;

/**
 * @author chenyao
 * @date 2020/12/13 13:26
 * @description 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 最长字符串前缀 {
    public static void main(String[] args) {
        getMax(new String[]{"ac", "ac", "a", "a"});
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了87.55%的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了54.35%的用户
     * @param strs
     * @return
     */
    public static String getMax(String[] strs) {
        if (strs.length == 0) return "";
        String first = strs[0];
        if (strs.length == 1) return first;
        boolean f = false;
        for (int index = 0; index < first.length(); index++) {
            char c = first.charAt(index);//数组中0下标的字符串 第i个下标的字符
            boolean flag = true;
            for (int j = 1; j < strs.length; j++) {
                String next = strs[j];//第n个字符串
                if (next.equals("")) return "";
                if (f && index == next.length()) return next;
                if (next.charAt(index) != c) {//第n个字符串的第i个下标的字符 与第一个比较是否相同
                    flag = false;
                }
            }
            if (!flag) {//代表一次遍历过程中 出现不同的前缀字符 直接返回
                return first.substring(0, index);
            }
            f = true;
        }

        return first;
    }
}
