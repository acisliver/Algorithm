package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

// 쉬운 최단거리
// https://www.acmicpc.net/problem/14940
public class N14940 {

    static int N, M;
    static int[][] DISTANCE;
    static int[] START;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = nm[0];
        M = nm[1];
        int[][] map = new int[N][M];
        DISTANCE = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    START = new int[]{i, j};
                }
            }
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(DISTANCE[i], -1);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    DISTANCE[i][j] = 0;
                }
            }
        }


        search(START[0], START[1], map);

        for (int i = 0; i < N; i++) {
            String result = Arrays.stream(DISTANCE[i])
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
            System.out.println(result);
        }
    }

    private static void search(int r, int  c, int[][] map) {
        final int[] dR = {0, 0, 1, -1};
        final int[] dC = {1, -1, 0, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        DISTANCE[r][c] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nR = cur[0] + dR[i];
                int nC = cur[1] + dC[i];

                if (nR < 0 || nC < 0 || nR >= N || nC >= M) continue;
                if (DISTANCE[nR][nC] > -1) continue;
                if (map[nR][nC] == 0) continue;

                DISTANCE[nR][nC] = DISTANCE[cur[0]][cur[1]] + 1;
                queue.offer(new int[]{nR, nC});
            }
        }

    }
}
