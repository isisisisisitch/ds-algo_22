package ca.bytetube._00_leetcode._01_list;

/**
 * https://leetcode.com/problems/reverse-linked-list/description/
 *
 * @author dal
 */
public class ReverseLinkedList {

    //recursion
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = reverseList1(head.next);
        //4
        head.next.next = head;
        head.next = null;

        return newHead;
    }


    //iteration
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
