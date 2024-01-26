package leetcode;

import java.util.Arrays;

// Out of Boundary Paths
public class N576 {
    private static final int MOD = 1_000_000_007;

    private int[][][] dp = new int[51][51][51];

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return backtrack(m, n, maxMove, startRow, startColumn);
    }

    private int backtrack(int m, int n, int move, int r, int c) {
        if (dp[r][c][move] != -1) {
            return dp[r][c][move];
        }

        if (move == 0) {
            return 0;
        }

        final int[] dR = {0, 0, 1, -1};
        final int[] dC = {1, -1, 0, 0};

        int path = 0;
        for (int i = 0; i < 4; i++) {
            int nR = r + dR[i];
            int nC = c + dC[i];
            if (nR < 0 || nC < 0 || nR >= m || nC >= n) {
                path += 1;
            } else {
                path += backtrack(m, n, move - 1, nR, nC);
                path %= MOD;
            }
        }

        dp[r][c][move] = path % MOD;
        return dp[r][c][move];
    }
}
