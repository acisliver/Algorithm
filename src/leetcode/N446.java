package leetcode;

import java.util.Arrays;

// Arithmetic Slices II - Subsequence
// https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
public class N446 {

    public static void main(String[] args) {
        N446 n446 = new N446();
        System.out.println(n446.numberOfArithmeticSlices(new int[]{0,2000000000,-294967296}));
    }

    public int numberOfArithmeticSlices(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], - 1);
        }

        int answer = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                answer += solve(i, j, 2, dp, nums);
            }
        }

//        System.out.println(Arrays.deepToString(dp).replace("], ", "]\n"));
        return answer;
    }

    private int solve(int prev, int cur, int count, int[][] dp, int[] nums) {
        if (cur >= nums.length - 1) {
            return 0;
        }

        if (dp[prev][cur] != -1) {
            return dp[prev][cur];
        }

        long diff = nums[cur] - nums[prev];
        int result = 0;
        for (int i = cur + 1; i < nums.length; i++) {
            if (nums[i] - nums[cur] == diff) {
                result += solve(cur, i, count + 1, dp, nums) + 1;
            }
        }

        return dp[prev][cur] = result;
    }
}
