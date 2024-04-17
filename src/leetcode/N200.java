package leetcode;

import java.util.LinkedList;
import java.util.Queue;

// Number of Islands
public class N200 {
    public int numIslands(char[][] grid) {
        int answer = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    visit(i, j, grid);
                    answer += 1;
                }
            }
        }

        return answer;
    }

    private void visit(int r, int c, char[][] grid) {
        final int[] dR = {0, 0, 1, -1};
        final int[] dC = {1, -1, 0, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        grid[r][c] = '0';

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nR = cur[0] + dR[i];
                int nC = cur[1] + dC[i];

                if (nR < 0 || nC < 0 || nR >= grid.length || nC >= grid[0].length) {
                    continue;
                }

                if (grid[nR][nC] == '0') {
                    continue;
                }

                grid[nR][nC] = '0';
                queue.offer(new int[]{nR, nC});
            }
        }
    }
}
