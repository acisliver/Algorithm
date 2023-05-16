package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 테트로미노
// https://www.acmicpc.net/problem/14500
public class N14500 {

    static int N, M, ANSWER;
    static int[][] BOARD;
    static boolean[][] VISITED;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ANSWER = 0;
        BOARD = new int[N][M];
        VISITED = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                BOARD[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                VISITED[r][c] = true;
                search(r, c, 1, BOARD[r][c]);
                VISITED[r][c] = false;
            }
        }

        System.out.println(ANSWER);
    }

    private static void search(int r, int c, int count, int sum) {
        int[] dR = {0, 0, 1, -1};
        int[] dC = {1, -1, 0, 0};

        if (count == 4) {
            ANSWER = Math.max(sum, ANSWER);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nR = r + dR[i];
            int nC = c + dC[i];

            if (nR < 0 || nC < 0 || nR >= N || nC >= M) continue;
            if (VISITED[nR][nC]) continue;

            // 다른 방향으로 이동
            if (count == 2) {
                VISITED[nR][nC] = true;
                search(r, c, count + 1, sum + BOARD[nR][nC]);
                VISITED[nR][nC] = false;
            }

            VISITED[nR][nC] = true;
            search(nR, nC, count + 1, sum + BOARD[nR][nC]);
            VISITED[nR][nC] = false;
        }
    }
}
