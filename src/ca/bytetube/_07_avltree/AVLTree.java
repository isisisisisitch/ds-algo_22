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
    public void afterRemove(Node<E> node) {//Node == AVLNode
        while ((node = node.parent) != null) {//node = root
            if (isBalanced(node)) {
                calculateHeight(node);
            } else {
                rebalance(node);
                //break;
            }
        }

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

    private void rebalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {//L
            if (node.isLeftChild()) {//L
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else {//R
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else {//R
            if (node.isLeftChild()) {//L
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);

            } else {//RR
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }

        }

    }

    private void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c,
                        Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        //1.让d成为子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) r.parent.left = d;
        if (r.isRightChild()) r.parent.right = d;
        else root = d;

        //2.左子树 abc
        b.left = a;
        if (a != null) a.parent = b;
        b.right = c;
        if (c != null) c.parent = b;
        calculateHeight(b);
        //3.右子树efg
        f.left = e;
        if (e != null) e.parent = f;
        f.right = g;
        if (g != null) g.parent = f;
        calculateHeight(f);
        //4.bdf
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        calculateHeight(d);

    }

    private void leftRotation(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;//T1
        grand.right = child;
        parent.left = grand;
        afterRotation(grand, parent, child);
    }

    private void rightRotation(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;//T2
        grand.left = child;
        parent.right = grand;
        afterRotation(grand, parent, child);

    }

    public void afterRotation(Node<E> grand, Node<E> parent, Node<E> child) {
        //update parent of T2、p、g
        //1.让parent成为子树的根节点（更新parent的父节点）
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }
        //2.更新child 的 parent
        if (child != null) {
            child.parent = grand;
        }
        //3.更新grand的parent
        grand.parent = parent;

        calculateHeight(grand);
        calculateHeight(parent);
    }

    @Override
    protected Node<E> createdNode(E element, Node<E> parent) {

        return new AVLNode<>(element, parent);
    }


    private void calculateHeight(Node<E> node) {//Node = AVLNode
        ((AVLNode<E>) node).calculateHeight();
    }

    private boolean isBalanced(Node<E> node) {
        int factor = ((AVLNode<E>) node).balanceFactor();
        return Math.abs(factor) <= 1;
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
