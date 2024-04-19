package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Find All Groups of Farmland
public class N1992 {
    public int[][] findFarmland(int[][] land) {
        List<int[]> answer = new ArrayList<>();

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (land[i][j] == 1) {
                    answer.add(search(i, j, land));
                }
            }
        }

        return answer.toArray(value -> new int[answer.size()][]);
    }

    private int[] search(int r, int c, int[][] land) {
        final int[] dR = {1, 0};
        final int[] dC = {0, 1};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        land[r][c] = 0;
        int[] coordinate = new int[4];
        coordinate[0] = r;
        coordinate[1] = c;
        coordinate[2] = r;
        coordinate[3] = c;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int j = 0; j < dR.length; j++) {
                    int nR = cur[0] + dR[j];
                    int nC = cur[1] + dC[j];

                    if (nR < 0 || nC < 0 || nR >= land.length || nC >= land[0].length) {
                        continue;
                    }

                    if (land[nR][nC] == 0) {
                        continue;
                    }

                    queue.offer(new int[]{nR, nC});
                    land[nR][nC] = 0;
                    coordinate[2] = nR;
                    coordinate[3] = nC;
                }
            }

        }

        return coordinate;
    }
}
