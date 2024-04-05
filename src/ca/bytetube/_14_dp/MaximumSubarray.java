package ca.bytetube._14_dp;

import javax.swing.text.MaskFormatter;

/**
 * https://leetcode.com/problems/maximum-subarray/description/
 *
 * @author dal
 */
public class MaximumSubarray {

    public static int maxSubArray(int[] nums) {
        //① Define state
        //Suppose dp(i) is the sum of its maximum contiguous subarray ending in nums[i]
        int dp = nums[0];
        //② Set initial state (boundary)
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            //③ Determine the state transition equation
            //1.if dp(i-1) <= 0 dp[i] = nums[i]
            if (dp <= 0) dp = nums[i];
                //2.else dp(i) = dp(i-1) + nums[i]
            else dp = dp + nums[i];
            max = Math.max(dp, max);

        }

        return max;
    }


    public static int maxSubArray1(int[] nums) {
        //① Define state
        //Suppose dp(i) is the sum of its maximum contiguous subarray ending in nums[i]
        int[] dp = new int[nums.length];
        //② Set initial state (boundary)
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            //③ Determine the state transition equation
            //1.if dp(i-1) <= 0 dp[i] = nums[i]
            if (dp[i - 1] <= 0) dp[i] = nums[i];
                //2.else dp(i) = dp(i-1) + nums[i]
            else dp[i] = dp[i - 1] + nums[i];
            max = Math.max(dp[i], max);

        }

        return max;
    }


}
