package ca.bytetube._17_sort._uncmp;

import ca.bytetube._17_sort._cmp.utils.Integers;

public class CountingSort {

    public static void main(String[] args) {
        Integer[] arr = {7, 3, 5, 8, 6, 7, 4, 5};
        Integers.println(arr);
        sort(arr);
        Integers.println(arr);

    }

    public static void sort(Integer[] array) {
        if (array == null || array.length == 0 || array.length == 1) return;

        //1.找最大值最小值
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) max = array[i];
            if (array[i] < min) min = array[i];
        }

        //2.创建counts数组，统计元素出现的次数
        int[] counts = new int[max - min + 1];
        //2.1先统计次数
        for (int i = 0; i < array.length; i++) counts[array[i] - min]++;
        //2.2 累加次数（从左到右）
        for (int i = 1; i < counts.length; i++) counts[i] += counts[i - 1];

        //3.做一个新数组用来存排序后的结果
        int[] newArr = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) newArr[--counts[array[i] - min]] = array[i];

        for (int i = 0; i < newArr.length; i++) array[i] = newArr[i];
    }


    public static void sort1(Integer[] array) {
        if (array == null || array.length == 0 || array.length == 1) return;

        //1.找最大值
        int max = array[0];
        for (int i = 0; i < array.length; i++) if (array[i] > max) max = array[i];

        //2.创建counts数组，用来统计元素出现的次数
        int[] counts = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            counts[array[i]]++;
        }

        int index = 0;
        //3.根据counts数组中values对应的数组元素进行排序
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-- > 0) {
                array[index++] = i;
            }

        }

    }
}
