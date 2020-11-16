package leetcode.t30;

import org.junit.Test;

/**
 * @author chenyao
 * @date 2019/9/4 17:46
 * @description
 */
public class 链表形式的数字相加Next002 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode temp = node;
        ListNode l1Temp = l1;
        ListNode l2Temp = l2;
        int jinwei = 0;
        while (l1Temp != null || l2Temp != null){
            int sum = (l1Temp == null ? 0 : l1Temp.val) + (l2Temp == null ? 0 : l2Temp.val) + jinwei;
            jinwei = sum >= 10 ? 1 : 0;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
            /**
             * 注释掉的三种情况 其实都会跳出循环 所以只需要在while循环外判断进位是否为1 即可
             */
//            if (l1Temp != null && l2Temp != null && l1Temp.next == null && l2Temp.next == null) {
//                if (jinwei == 1) {
//                    temp.next = new ListNode(1);
//                    break;
//                }
//            }
//            if (l1Temp != null && jinwei == 1 && l1Temp.next == null && l2Temp == null) {
//                temp.next = new ListNode(1);
//                break;
//            }
//            if (l2Temp != null && jinwei == 1 && l2Temp.next == null && (l1Temp == null)) {
//                temp.next = new ListNode(1);
//                break;
//            }
            if (l1Temp != null) {
                l1Temp = l1Temp.next;
            }
            if (l2Temp != null) {
                l2Temp = l2Temp.next;
            }
        }
        if(jinwei == 1){
            temp.next=new ListNode(1);
        }
        return node.next;
    }

    /**
     * 官方解题思路
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }


    @Test
    public void tgest() {
        ListNode t1 = new ListNode(1);
        t1.next = new ListNode(8);
        ListNode t2 = new ListNode(0);
        addTwoNumbers(t1, t2);
    }
}
