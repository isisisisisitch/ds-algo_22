package ca.bytetube._07_avltree;

import java.util.Comparator;

public class BinarySearchTree<E> extends BinaryTree<E> {
    private Comparator<E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    protected void afterAdd(Node<E> node){}

    // add elements
    public void add(E element) {
        if (element == null) throw new IllegalArgumentException("element can not be null !");
        //1.添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            afterAdd(root);
            return;
        }
        //2.添加的不是第一个节点
        Node<E> node = root;
        Node<E> parent = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) node = node.right;
            else if (cmp < 0) node = node.left;
            else {
                node.element = element;
                return;
            }
        }

        //3.把待加入点添加到指定的位置上
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) parent.right = newNode;
        else parent.left = newNode;
        afterAdd(newNode);
        size++;
    }

    private int compare(E e1, E e2) {
        if (comparator != null) return comparator.compare(e1, e2);
        return ((Comparable<E>) e1).compareTo(e2);
    }

    public Node<E> node(E element) {
        Node<E> node = root;

        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);

            if (cmp > 0) node = node.right;
            else if (cmp < 0) node = node.left;
            else {
                // node.element = element;
                return node;
            }
        }

        return null;
    }

    // remove elements
    public void remove(E element) {
        remove(node(element));
    }


    private void remove(Node<E> node) {
        size--;
        //3.Delete node-node with degree 2
        if (node.hasTwoChildren()) {
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;
        }

        //2.Delete node-node with degree 1
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node == root) root = replacement;
            else {
                if (node == node.parent.left) node.parent.left = replacement;
                else node.parent.right = replacement;
            }

        }
        //1.Delete node-leaf node
        //root node
        else if (node.parent == null) root = null;
        else {
            if (node == node.parent.left) node.parent.left = null;
            else node.parent.right = null;
        }

    }


    public boolean contains(E element) {

        return node(element) != null;
    }

}
