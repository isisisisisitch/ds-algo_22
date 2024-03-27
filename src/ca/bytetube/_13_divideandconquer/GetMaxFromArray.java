package ca.bytetube._13_divideandconquer;


public class GetMaxFromArray {
    public static void main(String[] args) {
        int[] arr = {21,34,2,56,-7,100,22,65,73,89};
        System.out.println(getMaxFromArray(arr));
    }

    public static int getMaxFromArray(int[] array) {

        if (array.length == 1) return array[0];
        return getMaxFromArray(array, 0, array.length - 1);
    }

    private static int getMaxFromArray(int[] array, int left, int right) {
        if (left == right) return array[left];
        int mid = (left + right) >> 1;
        int leftMax = getMaxFromArray(array, left, mid);
        int rightMax = getMaxFromArray(array, mid + 1, right);
        return Math.max(leftMax, rightMax);
    }
}
