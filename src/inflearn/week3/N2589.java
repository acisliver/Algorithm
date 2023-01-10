package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 보물섬
// https://www.acmicpc.net/problem/2589
public class N2589 {

    static int N, M;
    static boolean[][] MAP, VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        MAP = new boolean[N][M];

        String[] st;
        for (int i = 0; i < N; i++) {
            st = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                String cell = st[j];
                MAP[i][j] = !cell.equals("W");
            }
        }

        int distance = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (MAP[i][j]) {
                    VISITED = new boolean[N][M];
                    distance = Math.max(distance, getShortestDistance(i, j));
                }
            }
        }

        System.out.println(distance);
    }

    private static int getShortestDistance(int i, int j) {

        int[] dI = {1, -1, 0, 0};
        int[] dJ = {0 ,0, 1, -1};
        int[][] distances = new int[N][M];
        int distance = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        VISITED[i][j] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nextI = cur[0] + dI[k];
                int nextJ = cur[1] + dJ[k];

                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) continue;
                if (!MAP[nextI][nextJ]) continue;
                if (VISITED[nextI][nextJ]) continue;

                VISITED[nextI][nextJ] = true;
                distances[nextI][nextJ] = distances[cur[0]][cur[1]] + 1;
                distance = distances[nextI][nextJ];
                queue.offer(new int[]{nextI, nextJ});
            }
        }

        return distance;
    }

}
