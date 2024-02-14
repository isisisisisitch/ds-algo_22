package ca.bytetube._04_stack.single;

import ca.bytetube._04_stack.AbstractList;

public class SingleCircularLinkedList<E> extends AbstractList<E> {
    Node first;

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) node = node.next;
        return node;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        //头位置，第一个节点
        if (index == 0) {
            Node<E> newFirst = new Node<>(element, first);
                           //第一个节点
            Node<E> last = size == 0 ? newFirst : node(size - 1);
            last.next = newFirst;
            first = newFirst;
        } else {
            //普通位置，尾部
            Node<E> prevNode = node(index - 1);
            /**
             * "="2个功能
             * 1.赋值 int i = 10;  从右向左看
             * 2.指向 从左向右看
             */
            prevNode.next = new Node<>(element, prevNode.next);
        }
        size++;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return node(index).value;
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        Node<E> oldNode = node(index);
        E oldElement = oldNode.value;
        oldNode.value = element;
        return oldElement;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> removed = first;
        //头位置，最后1个节点
        if (index == 0) {
            if (size == 1) first = null;
           else {
                first = first.next;
                Node<E> last = node(size - 1);
                last.next = first;
            }
        } else {   //普通位置，尾部
            Node<E> prevNode = node(index - 1);//0
            removed = prevNode.next;//1
            prevNode.next = removed.next;
        }
        size--;

        return removed.value;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.value == null) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.value)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SingleLinkedList{size=").append(size).append(",elements=[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) sb.append(", ");
            sb.append(node.value);
            node = node.next;
        }
        sb.append("]}");
        return sb.toString();

    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
