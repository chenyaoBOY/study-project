package leetcode.t30;

/**
 * @author chenyao
 * @date 2019/9/4 16:04
 * @description
 */
public class 链表形式的数字相加002 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long total = getNum(l1) + getNum(l2);
        int count = String.valueOf(total).length();
        ListNode node = new ListNode(0);
        ListNode temp = node;
        for (int i = 0; i < count; i++) {
            temp.next = new ListNode(Integer.valueOf(String.valueOf(total % 10)));
            temp = temp.next;
            total = total / 10;
        }
        return node.next;
    }

    public static long getNum(ListNode ln) {
        long sum = 0;
        long ten = 1;
        ListNode next = ln;
        do {
            sum += next.val * ten;
            ten = ten * 10;
        } while ((next = next.next) != null);

        return sum;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 9, 9, 9, 9, 9, 9, 9, 9, 9};

        ListNode node = null;
        ListNode temp = null;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                node = new ListNode(arr[i]);
                temp = node;
            } else {
                temp.next = new ListNode(arr[i]);
                temp = temp.next;
            }
        }
        addTwoNumbers(node, new ListNode(9));
    }
}
