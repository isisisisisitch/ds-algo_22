package ca.bytetube._17_sort._cmp;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        sort(0, array.length);
    }

    private void sort(int begin, int end) {

        if (end - begin < 2) return;
        int pivot = pivotIndex(begin, end);
        sort(begin, pivot);
        sort(pivot + 1, end);

    }

    private int pivotIndex(int begin, int end) {
        //0.Select pivot elements randomly
        swap(begin, (int) (begin + Math.random() * (end - begin)));
        //1.备份pivot
        T pivot = array[begin];

        //2.通过begin，end位置上的元素值和pivot进行大小比较，进而确定移动方向
        end--;
        while (begin < end) {
            //2.1 从右向左看
            while (begin < end) {
                if (cmp(pivot, array[end]) < 0) end--;
                else {
                    array[begin++] = array[end];
                    break;
                }
            }

            //2.2 从左向右看
            while (begin < end) {
                if (cmp(pivot, array[begin]) > 0) begin++;
                else {
                    array[end--] = array[begin];
                    break;
                }

            }
        }

        //3.将pivot放到最后的begin和end相遇时的位置
        array[begin] = pivot;
        return begin;
    }
}
