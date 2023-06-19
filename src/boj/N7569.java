package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.System.in;

// 토마토
// https://www.acmicpc.net/problem/7569
public class N7569 {

    static int N, M, H, WALL;
    static int[][][] FARM;
    static boolean[][][] VISITED;
    static Queue<int[]> TOMATOS = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        M = input[0];
        N = input[1];
        H = input[2];
        WALL = 0;
        FARM = new int[H][N][M];
        VISITED = new boolean[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                FARM[i][j] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                for (int k = 0; k < M; k++) {
                    if (FARM[i][j][k] == 1) {
                        VISITED[i][j][k] = true;
                        TOMATOS.add(new int[]{i, j, k, 0});
                    }
                    if (FARM[i][j][k] == -1) {
                        VISITED[i][j][k] = true;
                        WALL += 1;
                    }
                }
            }
        }

        int answer = bfs();
        System.out.println(answer);
    }

    private static int bfs() {
        final int[] dI = {0, 0, 0, 0, 1, -1};
        final int[] dJ = {0, 0, 1, -1, 0, 0};
        final int[] dK = {1, -1, 0, 0, 0, 0};

        int rest = N * M * H - WALL - TOMATOS.size();

        if (rest == 0) return 0;

        int count = 0;

        while (!TOMATOS.isEmpty()) {
            int[] cur = TOMATOS.poll();
            int curI = cur[0];
            int curJ = cur[1];
            int curK = cur[2];

            for (int i = 0; i < 6; i++) {
                int nI = curI + dI[i];
                int nJ = curJ + dJ[i];
                int nK = curK + dK[i];

                if (nI < 0 || nJ < 0 || nK < 0 || nI >= H || nJ >= N || nK >= M) {
                    continue;
                }

                if (VISITED[nI][nJ][nK]) {
                    continue;
                }

                VISITED[nI][nJ][nK] = true;
                TOMATOS.add(new int[]{nI, nJ, nK, cur[3] + 1});
                rest -= 1;
                count = cur[3] + 1;
            }
        }

        return rest == 0 ? count : -1;
    }
}
