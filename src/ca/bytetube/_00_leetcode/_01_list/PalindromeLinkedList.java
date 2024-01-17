package ca.bytetube._00_leetcode._01_list;

/**
 * https://leetcode.com/problems/palindrome-linked-list/description/
 *
 * @author dal
 */
public class PalindromeLinkedList {
    /**
     * 解题思路：
     * 1.通过快慢指针找到单链表中点
     * 2.反转右半部分
     * 3.设定2个指针从两端向中间遍历，在遍历的过程中只要有一次不相等则return false
     * 4.恢复现场（反转右半部分）（？）
     */

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        //1.通过快慢指针找到单链表中点
        ListNode midNode = getMidNode(head);
        //2.反转右半部分
        ListNode rightHead = reverseList(midNode.next);
        //3.设定2个指针从两端向中间遍历，在遍历的过程中只要有一次不相等则return false
        ListNode p1 = head;
        ListNode p2 = rightHead;

        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        //reverseList(rightHead);
        return true;
    }

    public ListNode getMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        //头插
        ListNode newHead = null;

        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }


        return newHead;
    }
}
