package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 안전 영역
// https://www.acmicpc.net/problem/2468
public class N2468 {

    static int N;
    static int[][] HEIGHTS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        HEIGHTS = new int[N][N];

        for (int i = 0; i < N; i++) {
            HEIGHTS[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int maxSafeAreaCount = 1;   // 비가 오지 않는 경우
        for (int precipitation = 1; precipitation < 100; precipitation++) {
            int safeAreaCount = 0;
            boolean[][] visited = new boolean[N][N];
            for (int i = 0; i < HEIGHTS.length; i++) {
                for (int j = 0; j < HEIGHTS[0].length; j++) {
                    if (visited[i][j]) {
                        continue;
                    }
                    int height = HEIGHTS[i][j];
                    if (height > precipitation) {
                        safeAreaCount += 1;
                        dfs(i, j, precipitation, visited);
                    }
                }
            }
            maxSafeAreaCount = Math.max(safeAreaCount, maxSafeAreaCount);
        }

        System.out.println(maxSafeAreaCount);
    }

    private static void dfs(int row, int col, int precipitation, boolean[][] visited) {
        int[] dRow = {1, -1, 0, 0};
        int[] dCol = {0, 0, 1, -1};

        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) {
                continue;
            }

            if (visited[nextRow][nextCol]) {
                continue;
            }

            if (HEIGHTS[nextRow][nextCol] > precipitation) {
                dfs(nextRow, nextCol, precipitation, visited);
            }
        }

    }
}
