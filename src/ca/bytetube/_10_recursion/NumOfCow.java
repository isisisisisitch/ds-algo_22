package ca.bytetube._10_recursion;

public class NumOfCow {
    public static void main(String[] args) {
        System.out.println( numOfCow0(5));
        System.out.println( numOfCow(5));

    }

    public static int numOfCow0(int n) {
        if (n <= 4) return n + 1;
        return numOfCow0(n - 1) + numOfCow0(n - 4);
    }

    public static int numOfCow1(int n) {
        int[] arr = new int[n + 1];
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;

        for (int i = 5; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 4];
        }

        return arr[n];
    }

    public static int numOfCow(int n) {
        int[] arr = new int[n + 1];
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;

        return numOfCow(n,arr);
    }

    private static int numOfCow(int n, int[] arr) {
        if (arr[n] == 0) {
            arr[n] = numOfCow(n - 1, arr) + numOfCow(n - 4, arr);
        }

        return arr[n];
    }

}
