package ca.bytetube._00_leetcode._01_list;

/**
 * https://leetcode.com/problems/remove-linked-list-elements/description/
 *
 * @author dal
 */
public class RemoveLinkedListElements {
    //dummy node
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode(0);
        ListNode newTail = dummyNode;
        while (head != null) {
            if (head.val != val) newTail = newTail.next = head;
            head = head.next;
        }
        newTail.next = null;

        return dummyNode.next;
    }

    //构建新链表
    public ListNode removeElements2(ListNode head, int val) {
        ListNode newHead = null;
        ListNode newTail = null;

        while (head != null) {

            if (head.val != val) {
                if (newTail == null) {
                    newHead = head;
                    newTail = head;
                } else {
                    newTail.next = head;
                    newTail = head;
                }
            }

            head = head.next;
        }

        if (newTail == null) return null;
        else newTail.next = null;

        return newHead;

    }


    public ListNode removeElements1(ListNode head, int val) {

        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode current = head;

        while (current != null && current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }


}
