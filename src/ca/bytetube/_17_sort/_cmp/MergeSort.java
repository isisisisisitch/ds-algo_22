package ca.bytetube._17_sort._cmp;

public class MergeSort<T extends Comparable<T>> extends Sort<T> {
    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) (new Comparable[array.length >> 1]);
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if (end - begin < 2) return;
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);

        merge(begin, mid, end);

    }

    //只要是能来到这里做归并的两个数组，一定是有序数组
    private void merge(int begin, int mid, int end) {
        int li = 0;
        int le = mid - begin;
        int ri = mid;
        int re = end;
        int ai = begin;

        //1.备份左半部分
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        //2.merge left and right
        while (li < le) {
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }


    }
}
