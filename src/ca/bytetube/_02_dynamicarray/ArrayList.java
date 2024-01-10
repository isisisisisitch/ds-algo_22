package ca.bytetube._02_dynamicarray;


public class ArrayList<E> {

    private E[] elements;
    private int size;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    private int capacity = 15;

    // Number of elements
    public int size() {
        return size;
    }

    // Is it empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Clear all the elements
    public void clear() {
        size = 0;
    }


    // Add elements to the end
    public void add(E element) {
        add(size, element);
    }

    // Add elements to the index position
    public void add(int index, E element) {
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
        for (int i = index + 1; i < size; i++) {//i = size - 1
            elements[i - 1] = elements[i];
        }
        size--;

        return oldElement;
    }

    // Return the index of the element
    int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    //Contains a certain element
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }



    private void ensureCapacity(int capacity) {
        //1.获取老容量
        int oldCapacity = elements.length;

        //2.对比capacity>=老容量
        if (oldCapacity >= capacity) return;

        //3.对比capacity<老容量,扩容1.5倍（创建新容器）
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        //4.值的迁移
        for (int i = 0; i < size; i++) newElements[i] = elements[i];

        //5.返回扩容后的容器
        elements = newElements;
    }


    private void rangeCheck(int index) {
        if (index < 0 || index >= size) outOfBounds(index);
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) outOfBounds(index);
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("index:" + index + "," + "size:" + size);
    }
}
