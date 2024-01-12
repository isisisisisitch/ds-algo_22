package ca.bytetube._03_list;

public class SingleLinkedList<E> extends AbstractList<E> {
    Node<E> first;

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
        //head/first node
        if (index == 0) {
//            Node<E> firstNode = first;
//            Node<E> newFirst = new Node<>(element,null);
//            newFirst.next = firstNode;
//            first = newFirst;
            first = new Node<>(element, first);
        } else {
            //current/tail
            Node<E> prevNode = node(index - 1);
            //        //assign ===== right --->left
//        int i = 10;
//        //point to ==== left ---> right
            prevNode.next = new Node<>(element, prevNode.next);
        }

        size++;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);

        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        Node<E> oldNode = node(index);
        E oldVal = oldNode.element;
        oldNode.element = element;
        return oldVal;
    }

    @Override
    public E remove(int index) {
        Node<E> removed = first;
        //head/only one node
        if (index == 0) {
            first = first.next;
        } else {
            //current/tail
            Node<E> prevNode = node(index - 1);
            removed = prevNode.next;
            prevNode.next = removed.next;
        }

        size--;
        return removed.element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }

        } else {

            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }

        }
        return ELEMENT_NOT_FOUND;
    }

    private static class Node<E> {
        E element;
        Node<E> next;


        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    //size=3,elements=[a, b, c]
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size=").append(size).append(",elements=[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) sb.append(", ");
            sb.append(node.element);
            node = node.next;
        }
        sb.append("]");

        return sb.toString();
    }


}
