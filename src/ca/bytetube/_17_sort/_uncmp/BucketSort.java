package ca.bytetube._17_sort._uncmp;


import ca.bytetube._17_sort._cmp.utils.Integers;

import java.util.LinkedList;
import java.util.List;

public class BucketSort {
    public static void main(String[] args) {
        Double[] arr = {0.34, 0.47, 0.29, 0.84, 0.67, 0.54, 0.89, 0.43};
        sort(arr);
        for (Double element : arr) {
            System.out.print(element + " ");
        }
    }

    /**
     * 1.创建一定数量的桶（桶可以用数组，链表）
     * 2.按照一定的规则（不同的数据，规则不同），将序列中的元素尽可能均匀地分配到对应的桶中
     * 3.分别对每个桶进行单独排序
     * 4.将所有非空桶中的元素合并成有序数组
     * 时间复杂度： O(n) + O(n*logn/m) = O（n+n*logn/m ） = O(n + n*logn - n*logm)
     * 空间复杂度：O（n+m）
     */
    public static void sort(Double[] array) {
        if (array == null || array.length == 0 || array.length == 1) return;

        //1.创建一定数量的桶（桶可以用数组，链表）
        //O()
        List<Double>[] buckets = new LinkedList[array.length];

        //2.按照一定的规则（不同的数据，规则不同），将序列中的元素尽可能均匀地分配到对应的桶中
        //O(n)
        for (int i = 0; i < array.length; i++) {

            int bucketIndex = (int) (array[i] * array.length);
            List<Double> bucket = buckets[bucketIndex];
            if (bucket == null) {
                bucket = new LinkedList<>();
                buckets[bucketIndex] = bucket;
            }

            bucket.add(array[i]);

        }

        int index = 0;
        // 3.分别对每个桶进行单独排序
        //m * n/m*logn/m = O(n*logn/m)
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) continue;
            buckets[i].sort(null);
            //4.将所有非空桶中的元素合并成有序数组
            for (Double element : buckets[i]) {
                array[index++] = element;
            }
        }

    }
}
