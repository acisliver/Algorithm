package programers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/87694?language=java#
// 아이템 줍기
public class N87694 {
    static boolean[][] MAP = new boolean[104][104];
    static boolean[][] LINE = new boolean[104][104];

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int[] rec : rectangle) {
            int lx = rec[0] * 2;
            int ly = rec[1] * 2;
            int rx = rec[2] * 2;
            int ry = rec[3] * 2;
            for (int i = lx; i <= rx; i++) {
                if (MAP[ly][i]) LINE[ly][i] = true;
                if (MAP[ry][i]) LINE[ry][i] = true;
                MAP[ly][i] = true;
                MAP[ry][i] = true;
            }
            for (int i = ly; i <= ry; i++) {
                if (MAP[i][lx]) LINE[i][lx] = true;
                if (MAP[i][rx]) LINE[i][rx] = true;
                MAP[i][lx] = true;
                MAP[i][rx] = true;
            }
        }

        makeLine();
        for (int i = 0; i < MAP.length; i++) {
            System.out.println(Arrays.toString(LINE[i]));
        }
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2;
    }

    private void makeLine() {
        int[] dX = {0, 0, 1, -1};
        int[] dY = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[104][104];
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            for (int i = 0; i < 4; i++) {
                int nX = curX + dX[i];
                int nY = curY + dY[i];
                if (nX < 0 || nY < 0 || nX >= LINE.length || nY >= LINE.length) continue;
                if (visited[nY][nX]) continue;
                if (MAP[nY][nX]) {
                    LINE[nY][nX] = true;
                    visited[nY][nX] = true;
                } else {
                    queue.offer(new int[]{nX, nY});
                    visited[nY][nX] = true;
                }
            }
        }
    }

    private int bfs(int charX, int charY, int iX, int iY) {
        int[] dX = {0, 0, 1, -1};
        int[] dY = {1, -1, 0, 0};
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[104][104];
        queue.offer(new int[]{charX, charY});
        visited[charY][charX] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int[] cur = queue.poll();
                int curX = cur[0];
                int curY = cur[1];
                if (curX == iX && curY == iY) {
                    return count;
                }
                for (int i = 0; i < 4; i++) {
                    int nX = curX + dX[i];
                    int nY = curY + dY[i];
                    if (visited[nY][nX]) continue;
                    if (!LINE[nY][nX]) continue;
                    queue.offer(new int[]{nX, nY});
                    visited[nY][nX] = true;
                }
            }

            count += 1;
        }

        return count;
    }
}
