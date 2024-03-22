package ca.bytetube._10_recursion;

public class TailRecursion {
    public static void main(String[] args) {
        System.out.println(factorial0(30));
        System.out.println(factorial(30));
    }

    public static int factorial0(int n) {
        if (n <= 1) return n;
        return n * factorial0(n - 1);
    }

    public static int factorial(int n) {

        return factorial(n, 1);
    }

    private static int factorial(int n, int res) {
        if (n <= 1) return res;
        return factorial(n - 1, res * n);
    }
}
