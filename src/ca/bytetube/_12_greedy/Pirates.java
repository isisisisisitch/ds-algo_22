package ca.bytetube._12_greedy;

import java.util.Arrays;

public class Pirates {
    public static void main(String[] args) {
        System.out.println("count:" + maxNumOfAntiques(30, new int[]{3, 5, 4, 10, 7, 14, 2, 11}));
    }


    public static int maxNumOfAntiques(int capacity, int[] weights) {
        Arrays.sort(weights);

        int count = 0;
        int weight = 0;
        for (int i = 0; i < weights.length && weight < capacity; i++) {
            int newWeight = weights[i] + weight;
            if (newWeight <= capacity) {
                count++;
                weight = newWeight;
                System.out.println(weights[i]);
            }
        }


        return count;
    }
}
