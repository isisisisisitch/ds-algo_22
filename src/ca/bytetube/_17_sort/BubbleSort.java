package ca.bytetube._17_sort;

import ca.bytetube._17_sort.utils.Integers;

public class BubbleSort {

    public static void main(String[] args) {
        Integer[] randomArr = Integers.random(20, -100, 100);
        Integers.println(randomArr);
        System.out.println("===============================");
        sort(randomArr);
        Integers.println(randomArr);
    }

    public static void sort(Integer[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            int sortedIndex = 0;
            for (int start = 1; start <= end; start++) {

                if (arr[start] < arr[start - 1]) {
                    int temp = arr[start];
                    arr[start] = arr[start - 1];
                    arr[start - 1] = temp;
                    sortedIndex = start;//start就是最后一个交换的位置
                }
            }

            end = sortedIndex;
        }

    }

    //switch status: ON/OFF  boolean enum
    public static void sort2(Integer[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int start = 1; start <= end; start++) {

                if (arr[start] < arr[start - 1]) {
                    int temp = arr[start];
                    arr[start] = arr[start - 1];
                    arr[start - 1] = temp;
                    sorted = false;
                }
            }

            if (sorted) break;
        }

    }

    public static void sort1(Integer[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            for (int start = 1; start <= end; start++) {
                if (arr[start] < arr[start - 1]) {
                    int temp = arr[start];
                    arr[start] = arr[start - 1];
                    arr[start - 1] = temp;
                }
            }
        }

    }
}
