package leetcode;

// https://leetcode.com/problems/maximum-subarray/
// 동적 프로그래밍
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
