package leetcode;

import java.util.Arrays;

// Perfect Squares
public class N279 {

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 10001);
        dp[0] = 0;
        dp[1] = 1;
        return search(n, dp);
    }

    private int search(int tar, int[] dp) {
        if (dp[tar] != 10001) {
            return dp[tar];
        }

        int square = (int) Math.sqrt(tar);

        int result = 10001;
        for (int i = square; i > 0; i--) {
            int pow = (int) Math.pow(i, 2);

            if (tar - pow < 0) {
                continue;
            }
            System.out.printf("%d, %d\n", tar, pow);
            result = Math.min(result, search(tar - pow, dp) + 1);
        }

        return dp[tar] = result;
    }
}
