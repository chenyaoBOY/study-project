package acm.challenge.org.string;

/**
 * @author chenyao
 * @date 2020/12/13 11:25
 * @description 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 最长回文字符串 {
    public static void main(String[] args) {
//        String maxString = getMaxString("cbbd");
//        System.out.println(maxString);
        System.out.println("sdf".substring(0,0));
    }

    /**
     * 执行用时：399 ms, 在所有 Java 提交中击败了12.15%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了61.42%的用户
     * @param s
     * @return
     */
    public static String getMaxString(String s) {
        String ans = "";
        int len = s.length();
        if (len == 0 || len == 1) return s;

        for (int i = 0; i < len; i++) {
            //每一次的循环代表 下标右移一次 也代表上次计算结果已出炉
            if (ans.length() >= len - i) {
                return ans;
            }
            int j = i + 1;
            //这里要计算区间[i,j]内的最长结果
            while (j < len) {
                if(ans.length()>(j-i)){
                    j++;
                    continue;
                }
                int left = i, right = j;
                while (left <= right) {
                    if (s.charAt(left) != s.charAt(right)) {
                        break;
                    }
                    left++;
                    right--;
                }
                if (left > right) {
                    if (ans.length() < (j - i + 1)) {
                        ans = s.substring(i, j + 1);
                    }
                }
                j++;
            }
        }
        if (ans.equals("")) {
            return s.substring(0, 1);
        }

        return ans;
    }
}
