package ca.bytetube._17_sort._cmp;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 12, 14};
        System.out.println(indexOf(3, arr));
    }

    //利用binary search查找v在有序数组中插入位置
    public static int search(int v, int[] array) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;

        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v < array[mid]) end = mid;
            else begin = mid + 1;
        }

        return begin;
    }


    //bs
    public static int indexOf(int v, int[] array) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;

        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v == array[mid]) return mid;
            else if (v < array[mid]) end = mid;
            else begin = mid + 1;
        }

        return -1;
    }
}
