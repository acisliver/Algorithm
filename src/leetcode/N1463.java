package leetcode;

import java.util.Arrays;

// Cherry Pickup II
public class N1463 {
    public int cherryPickup(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][][] dp = new int[row][col][col];

        for (int[][] intss : dp) {
            for (int[] ints : intss) {
                Arrays.fill(ints, -1);
            }
        }

        return search(0, 0, col - 1, dp, grid);
    }

    private int search(int i, int j1, int j2, int[][][] dp, int[][] grid) {
        if (i == grid.length) {
            return 0;
        }

        if (dp[i][j1][j2] != -1) {
            return dp[i][j1][j2];
        }

        final int[] ds = {-1, 0, 1};

        int result = 0;

        if (j1 == j2) {
            result += grid[i][j1];
        } else {
            result += grid[i][j1] + grid[i][j2];
        }

        int temp = 0;
        for (int d1 : ds) {
            int nJ1 = j1 + d1;
            if (nJ1 < 0 || nJ1 >= grid[0].length) {
                continue;
            }
            for (int d2 : ds) {
                int nJ2 = j2 + d2;
                if (nJ2 < 0 || nJ2 >= grid[0].length) {
                    continue;
                }

                temp = Math.max(temp, search(i + 1, nJ1, nJ2, dp, grid));
            }
        }

        dp[i][j1][j2] = result + temp;
        return dp[i][j1][j2];
    }
}
