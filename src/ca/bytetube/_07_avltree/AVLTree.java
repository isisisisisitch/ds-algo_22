package ca.bytetube._07_avltree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<E> extends BinarySearchTree<E> {
    public AVLTree() {
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    public void afterAdd(Node<E> node) {//Node == AVLNode
        while ((node = node.parent) != null) {//node = root
            if (isBalanced(node)) {
                calculateHeight(node);
            } else {
                rebalance(node);
                break;
            }
        }


    }


    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        if (parent.isLeftChild()) {//L
            if (node.isLeftChild()) {//L
                rightRotation(grand);
            } else {//R
                leftRotation(parent);
                rightRotation(grand);
            }

        } else {//R
            if (node.isLeftChild()) {//L
                rightRotation(parent);
                leftRotation(grand);

            } else {//RR
                leftRotation(grand);
            }

        }
    }

    private void leftRotation(Node<E> parent) {

    }

    private void rightRotation(Node<E> grand) {

    }

    private void calculateHeight(Node<E> node) {//Node = AVLNode
        ((AVLNode<E>) node).calculateHeight();
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }


    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>) this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>) this.right).height;
            return leftHeight - rightHeight;
        }

        public void calculateHeight() {
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>) this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>) this.right).height;
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        public Node<E> tallerChild() {
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>) this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>) this.right).height;
            if (leftHeight > rightHeight) return left;
            if (rightHeight > leftHeight) return right;
            return this == this.parent.left ? left : right;
        }
    }
}
