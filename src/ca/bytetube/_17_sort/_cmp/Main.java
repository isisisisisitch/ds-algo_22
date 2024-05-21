package ca.bytetube._17_sort._cmp;

import ca.bytetube._17_sort._cmp.utils.Asserts;
import ca.bytetube._17_sort._cmp.utils.Integers;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] randomArray = Integers.random(20000, -100000, 100000);
        //Integers.println(randomArray);
//        System.out.println("===============================");
//        HeapSort<Integer> heapSort = new HeapSort<>();
//        heapSort.sort(randomArray);
        // Integers.println(randomArray);
        testSorts(randomArray,new MergeSort(),new QuickSort(),new ShellSort());

    }

    public static void testSorts(Integer[] randomArray, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(randomArray);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));

        }

        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }

    }
}
