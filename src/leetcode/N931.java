package leetcode;

import java.util.Arrays;

// Minimum Falling Path Sum
public class N931 {

    public static void main(String[] args) {
        N931 n = new N931();
        System.out.println(n.minFallingPathSum(new int[][]{
                {2,1,3},{6,5,4},{7,8,9}
        }));
    }

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        if (n == 1) {
            return matrix[0][0];
        }

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 123456789);
        }

        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = -1; k < 2; k++) {
                    if (j + k < 0 || j + k >= n) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + k] + matrix[i][j]);
                }
            }
        }

        int answer = 123456789;
        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, dp[n - 1][i]);
        }

        return answer;
    }
}
