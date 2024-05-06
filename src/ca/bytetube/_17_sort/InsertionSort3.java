package ca.bytetube._17_sort;

public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            T v = array[begin];
            int insertIndex = search(begin);
            for (int i = begin; i > insertIndex; i--) {
                array[i] = array[i - 1];
            }

            array[insertIndex] = v;


        }
    }

    //利用binary search查找v在有序数组中插入位置
    //[0,index)范围内的数组已经有序
    public int search(int index) {

        int begin = 0;
        int end = index;

        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(array[index], array[mid]) < 0) end = mid;
            else begin = mid + 1;
        }

        return begin;
    }
}
