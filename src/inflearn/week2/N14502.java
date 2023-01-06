package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 연구소
// https://www.acmicpc.net/problem/14502
public class N14502 {

    static int N, M;
    static int[][] LABORATORY;
    static List<int[]> VIRUS_POINTS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        LABORATORY = new int[N][M];
        VIRUS_POINTS = new ArrayList<>();
        List<int[]> walls = new ArrayList<>();
        int maxSafeArea = 0;

        for (int i = 0; i < N; i++) {
            int[] inputRow = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            LABORATORY[i] = inputRow;
            for (int j = 0; j < M; j++) {
                if (LABORATORY[i][j] == 2) {
                    VIRUS_POINTS.add(new int[]{i, j});
                } else if (LABORATORY[i][j] == 0) {
                    walls.add(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < walls.size(); i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    int[] wall1 = walls.get(i);
                    int[] wall2 = walls.get(j);
                    int[] wall3 = walls.get(k);
                    LABORATORY[wall1[0]][wall1[1]] = 1;
                    LABORATORY[wall2[0]][wall2[1]] = 1;
                    LABORATORY[wall3[0]][wall3[1]] = 1;
                    maxSafeArea = Math.max(maxSafeArea, getSafeArea());
                    LABORATORY[wall1[0]][wall1[1]] = 0;
                    LABORATORY[wall2[0]][wall2[1]] = 0;
                    LABORATORY[wall3[0]][wall3[1]] = 0;
                }
                
            }            
        }
        
        System.out.println(maxSafeArea);
    }

    private static int getSafeArea() {
        int safeArea = 0;
        boolean[][] visited = new boolean[N][M];
        for (int[] virusPoint : VIRUS_POINTS) {
            visited[virusPoint[0]][virusPoint[1]] = true;
            dfs(virusPoint[0], virusPoint[1], visited);
        }

        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                if (LABORATORY[i][j] == 0 && !visited[i][j]) {
                    safeArea += 1;
                }
            }
        }

        return safeArea;
    }

    private static void dfs(int row, int col, boolean[][] visited) {
        int[] dRow = {1, -1, 0, 0};
        int[] dCol = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) {
                continue;
            }

            if (LABORATORY[nextRow][nextCol] == 1) {
                continue;
            }

            if (visited[nextRow][nextCol]) {
                continue;
            }

            visited[nextRow][nextCol] = true;
            dfs(nextRow, nextCol, visited);
        }

    }
}
