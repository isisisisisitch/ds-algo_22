package ca.bytetube._10_recursion;

/**
 * https://leetcode.com/problems/fibonacci-number/description/
 *
 * @author dal
 */
public class Fib {


    public int fib0(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal argument !");
        if (n <= 1) return n;

        return fib0(n - 1) + fib0(n - 2);
    }

    public int fib1(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal argument !");
        if (n <= 1) return n;

        int[] arr = new int[n + 1];//0
        arr[1] = 1;
        arr[2] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr[n];
    }

//    int[] arr;
//
//    public Fib(int n) {
//        arr = new int[n + 1];
//    }
//
//    public int fib2(int n) {
//        if (n < 0 || n > 30) throw new RuntimeException("illegal argument !");
//        if (n <= 1) return n;
//
//        arr[1] = 1;
//        arr[2] = 1;
//        int res = fib2(n - 1) + fib2(n - 2);
//        arr[n] = res;
//
//        return arr[n];
//    }


    public int fib(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal argument !");
        if (n <= 1) return n;

        int[] arr = new int[n + 1];//0
        arr[1] = 1;
        arr[2] = 1;

        return fib(n, arr);
    }

    private int fib(int n, int[] arr) {//1,arr
        if (arr[n] == 0) {
            arr[n] = fib(n - 1, arr) + fib(n - 2, arr);
        }

        return arr[n];
    }


}
