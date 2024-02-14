package ca.bytetube._04_stack.single;

import ca.bytetube._04_stack.AbstractList;

public class ArrayList<E> extends AbstractList<E> {
    private E[] elements;//null
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        this.elements = (E[]) new Object[capacity];
    }

    // Clear all the elements
    public void clear() {
        size = 0;
    }


    // Add elements to the index position
    public void add(int index, E element) {// 2. size= 3; index= -100;element = 100;
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) elements[i] = elements[i - 1];
        elements[index] = element;
        size++;

    }

    // Returns the element corresponding to the index position
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }


    // Set the element at the index position
    public E set(int index, E element) {
        rangeCheck(index);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }


    // Delete elements to the index position
    public E remove(int index) {
        rangeCheck(index);
        E oldElement = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        return oldElement;
    }


    // Return the index of the element
    public int indexOf(E element) {//new Person("tony",27);
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                // new Person("test")
                if (element.equals(elements[i])) return i;
            }
        }

        return ELEMENT_NOT_FOUND;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayList{size=").append(size).append(",elements=[");
        for (int i = 0; i < size; i++) {
            if (i != 0) sb.append(", ");
            sb.append(elements[i]);
        }
        sb.append("]}");
        return sb.toString();

    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        //扩容
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) newElements[i] = elements[i];

        elements = newElements;

    }



}
