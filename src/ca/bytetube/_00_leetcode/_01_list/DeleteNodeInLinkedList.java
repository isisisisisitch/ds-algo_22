package ca.bytetube._00_leetcode._01_list;

/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/description/
 *
 * @author dal
 * <p>
 * You are given the node to be deleted node.
 * You will not be given access to the first node of head.
 * <p>
 * All the values of the linked list are unique,
 * and it is guaranteed that the given node node is not the last node in the linked list
 */
public class DeleteNodeInLinkedList {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
