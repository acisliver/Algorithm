package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

// 토마토
// https://www.acmicpc.net/problem/7576
public class N7576 {

    static int N, M, WALL;
    static int[][] MAP;
    static Queue<int[]> TOMATOS;
    static boolean[][] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[1];
        M = input[0];
        WALL = 0;
        MAP = new int[N][M];
        TOMATOS = new LinkedList<>();
        VISITED = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
                if (MAP[i][j] == 1) {
                    TOMATOS.add(new int[]{i, j});
                    VISITED[i][j] = true;
                }
                if (MAP[i][j] == -1) {
                    WALL += 1;
                }
            }
        }
        int answer = bfs();
        System.out.println(answer);
    }

    private static int bfs() {
        final int[] dR = {0, 0, 1, -1};
        final int[] dC = {1, -1, 0, 0};

        int result = 0;
        int rest = N * M - WALL - TOMATOS.size();
        while (!TOMATOS.isEmpty()) {
            int size = TOMATOS.size();

            for (int i = 0; i < size; i++) {
                int[] cur = TOMATOS.poll();
                int cR = cur[0];
                int cC = cur[1];
                for (int j = 0; j < 4; j++) {
                    int nR = cR + dR[j];
                    int nC = cC + dC[j];
                    if (nR < 0 || nC < 0 || nR >= N || nC >= M) continue;
                    if (VISITED[nR][nC]) continue;
                    if (MAP[nR][nC] == -1) continue;

                    VISITED[nR][nC] = true;
                    TOMATOS.add(new int[]{nR, nC});
                    rest -= 1;
                }
            }

            result += 1;
        }

        if (rest == 0) return result - 1;

        return -1;
    }
}
