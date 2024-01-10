package ca.bytetube._01_complexity;

/**
 * https://leetcode.com/problems/fibonacci-number/description/
 *
 * @author dal
 */
public class FibonacciNumber {

    public static void main(String[] args) {
        Times.test("fib1", () -> {
            fib1(50);
        });

        Times.test("fib", () -> {
            fib2(50);
        });
    }


    public static int fib(int n) {
        if (n <= 1) return n;
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }


        return second;
    }

    //在递归过程中，数据共享的方法：
    //1.作为递归函数的参数进行传递
    //2.把需要共享的数据做成成员变量
    public static int fib2(int n) {
        if (n <= 1) return n;

        int[] arr = new int[n + 1];//element = 0
        arr[1] = 1;
        arr[2] = 1;

        return fib2(n, arr);
    }

    private static int fib2(int n, int[] arr) {
        if (arr[n] == 0) {
            arr[n] = fib2(n - 1, arr) + fib2(n - 2, arr);
        }

        return arr[n];
    }


    //fib(0) = 0, fib(1) = 1 ,fib(n) = fib(n-1) + fib(n-2)

    // n = 4 ---> fib(4)
    //fib(4) = fib(3) + fib(2) = 2 + 1 = 3
    //fib(3) = fib(2) + fib(1) = 1 + 1 = 2
    //fib(2) = fib(1) + fib(0) = 1 + 0 = 1
    public static int fib1(int n) {

        if (n <= 1) return n;

        return fib1(n - 1) + fib1(n - 2);

    }


}
