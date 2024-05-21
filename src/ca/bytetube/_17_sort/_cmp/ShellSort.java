package ca.bytetube._17_sort._cmp;


import java.util.ArrayList;
import java.util.List;

public class ShellSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        List<Integer> stepSequence = stepSequence();
        for (Integer step : stepSequence) {
            sort(step);
        }

    }


    protected void sort(int step) {

        for (int col = 0; col < step; col++) {//对每列进行排序
            //insertion sort
            for (int begin = col + step; begin < array.length; begin += step) {
                int cur = begin;
                while (cur > col && cmp(cur, cur - step) < 0) {
                    swap(cur, cur - step);
                    cur -= step;
                }
            }
        }

    }

    private List<Integer> stepSequence() {//n/2^k.  n is 16, the step sequence is {1, 2, 4, 8}
        List<Integer> stepSequence = new ArrayList<>();
        int step = array.length;//16

        while ((step = step >> 1) > 0) {
            stepSequence.add(step);
        }

        return stepSequence;
    }
}
