package ca.bytetube._14_dp;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * @author dal
 */
public class LIS {
    public int lengthOfLIS(int[] nums) {
        //① Define state
        int[] dp = new int[nums.length];

        //② Set initial state
        dp[0] = 1;
        int maxLength = dp[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                //③ Determine the state transition equation
                //if nums[i] ≤ nums[j]
                if (nums[i] <= nums[j]) continue;
                //if nums[i] > nums[ j] dp(i) = max { dp(i), dp( j) + 1 }
                dp[i] = Math.max(dp[i], dp[j] + 1);

            }
            maxLength = Math.max(dp[i], maxLength);
        }


        return maxLength;
    }
}
