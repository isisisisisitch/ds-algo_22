package ca.bytetube._13_divideandconquer;

/**
 * https://leetcode.com/problems/maximum-subarray/description/
 *
 * @author dal
 */
public class MaximumSubarray {

    public static int maxSubArray(int[] nums) {
        return maxSubArray(nums, 0, nums.length);
    }

    public static int maxSubArray(int[] nums, int begin, int end) {
        if (end - begin < 2) return nums[begin];

        int mid = (begin + end) >> 1;

        int leftSum = 0;
        int leftMax = Integer.MIN_VALUE;
        for (int i = mid - 1; i >= begin; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        int rightSum = 0;
        int rightMax = Integer.MIN_VALUE;
        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        return Math.max(Math.max(maxSubArray(nums, begin, mid), maxSubArray(nums, mid, end)),
                leftMax + rightMax);


    }


    public static int maxSubArray2(int[] nums) {
        int maxSum = Integer.MIN_VALUE;

        for (int start = 0; start < nums.length; start++) {
            int currentSum = 0;
            for (int end = start; end < nums.length; end++) {
                currentSum += nums[end];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }


    public int maxSubArray1(int[] nums) {

//        int start = 0;
//        int end = 0;
//        int max = Integer.MIN_VALUE;
//        while (start <= nums.length - 1) {
//            int sum = nums[start];
//            while (end <= nums.length - 1) {
//                if (end != start) sum += nums[end];
//                if (sum > max) max = sum;
//                end++;
//            }
//            start++;
//            end = start;
//        }
//        return max;
        if (nums.length == 1) return nums[0];

        int maxSum = nums[0];
        for (int start = 0; start < nums.length; start++) {
            for (int end = start; end < nums.length; end++) {
                int sum = findSum(nums, start, end);
                if (sum > maxSum) maxSum = sum;
            }
        }
        return maxSum;
    }

    public int findSum(int[] nums, int start, int end) {
        int sum = 0;
        while (start <= end) {
            sum += nums[start++];
        }
        return sum;
    }
}
