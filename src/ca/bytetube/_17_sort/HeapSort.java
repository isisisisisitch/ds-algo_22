package ca.bytetube._17_sort;

public class HeapSort<T extends Comparable<T>> extends Sort<T> {

    private int size;

    @Override
    protected void sort() {
        //① heapify
        size = array.length;
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
        //② Repeat the following operations
        //until there is only 1 element in the heap,
        // Swap the top and tail elements of the heap
        // Decrease the number of elements
        //in the heap by 1
        // Then do siftDown operation on the 0 position
        while (size > 1) {
            swap(0, --size);
            siftDown(0);
        }

    }

    private void siftDown(int index) {
        int half = size >> 1;
        T element = array[index];
        //siftDown的时候必须保证index是非叶节点
        while (index < half) {
            //index分2种情况
            //1.left，right都有
            //2.只有left
            //默认和left比较
            int childIndex = (index << 1) + 1;
            T child = array[childIndex];
            int rightChildIndex = childIndex + 1;

            if (rightChildIndex < size && cmp(array[rightChildIndex], child) > 0) {
                child = array[childIndex = rightChildIndex];
            }

            if (cmp(element, child) >= 0) break;
            array[index] = child;
            index = childIndex;

        }

        array[index] = element;

    }
}
