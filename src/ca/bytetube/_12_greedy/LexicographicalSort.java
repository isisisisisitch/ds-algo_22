package ca.bytetube._12_greedy;

import java.util.Arrays;
import java.util.Comparator;

public class LexicographicalSort {
    public static void main(String[] args) {
        System.out.println(lexicographicalSort(new String[]{"ba","b"}));
    }

    public static String lexicographicalSort(String[] strings) {
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            res.append(strings[i]);

        }
        return res.toString();
    }
}
