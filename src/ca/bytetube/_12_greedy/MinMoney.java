package ca.bytetube._12_greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinMoney {
    public static void main(String[] args) {
        int minMoney1 = minMoney1(new int[]{10, 20, 30, 40, 50});
        System.out.println(minMoney1);
        int minMoney = minMoney(new int[]{10, 20, 30, 40, 50});
        System.out.println(minMoney);
    }

    public static int minMoney(int[] arr) {
        //1.将数据小根堆化
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int data : arr) queue.add(data);

        int cost = 0;
        while (queue.size() > 1) {
            int subCost = queue.poll() + queue.poll();
            cost += subCost;
            queue.add(subCost);
        }

        return cost;
    }

    public static int minMoney1(int[] arr) {
        Arrays.sort(arr);

        int count = arr.length;
        int cost = 0;
        for (int i = count - 1; i > 0; i--) {
            int subCost = sum(arr, i);
            cost += subCost;
        }
        return cost;
    }

    private static int sum(int[] arr, int length) {
        int res = 0;
        for (int i = length; i >= 0; i--) {
            res += arr[i];
        }
        return res;

    }
}
