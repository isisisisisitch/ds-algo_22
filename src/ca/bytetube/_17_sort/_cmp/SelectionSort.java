package ca.bytetube._17_sort._cmp;

import ca.bytetube._17_sort._cmp.utils.Integers;

public class SelectionSort {
    public static void main(String[] args) {
        Integer[] randomArr = Integers.random(20, -100, 100);
        Integers.println(randomArr);
        System.out.println("===============================");
        sort(randomArr);
        Integers.println(randomArr);
    }

    public static void sort(Integer[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int start = 1; start <= end; start++) {
                if (arr[maxIndex] < arr[start]) {
                    maxIndex = start;
                }
            }

            int temp = arr[maxIndex];
            arr[maxIndex] = arr[end];
            arr[end] = temp;
        }

    }
}
