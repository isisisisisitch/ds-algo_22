package ca.bytetube._14_dp;

public class FindAimInArray {
    public static void main(String[] args) {

    }
    //todo
    public static boolean findAimInArray(int[] arr, int aim) {

        boolean[][] dp = new boolean[arr.length + 1][aim + 1];


        return dp[0][0];
    }


    public static boolean findAimInArray1(int[] arr, int aim) {
        return findAimInArray(arr, 0, 0, aim);

    }

    public static boolean findAimInArray(int[] arr, int index, int sum, int aim) {
        if (sum == aim) return true;
        if (index == arr.length) return false;
        return findAimInArray(arr, index + 1, sum + arr[index], aim) ||
                findAimInArray(arr, index + 1, sum, aim);
    }
}