package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 게임
// https://www.acmicpc.net/problem/1103
public class N1103 {

    static int N, M, MAX;
    static int[][] BOARD;
    static int[][] DP;
    static boolean[][] VISITED;
    static boolean IS_CYCLE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        MAX = 0;
        BOARD = new int[N][M];
        DP = new int[N][M];
        VISITED = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (chars[j] == 'H') {
                    BOARD[i][j] = -1;
                } else {
                    BOARD[i][j] = Character.getNumericValue(chars[j]);
                }
            }
        }

        VISITED[0][0] = true;
        play(0, 0);
        System.out.println(IS_CYCLE ? -1 : DP[0][0]);
    }

    private static int play(int r, int c) {

        if (DP[r][c] > 0) {
            return DP[r][c];
        }

        int[] dR = {0, 0, 1, -1};
        int[] dC = {1, -1, 0, 0};

        int move = BOARD[r][c];

        for (int i = 0; i < 4; i++) {
            int nextR = r + move * dR[i];
            int nextC = c + move * dC[i];

            if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M) continue;
            if (BOARD[nextR][nextC] == -1) continue;
            if (VISITED[nextR][nextC]) {
                IS_CYCLE = true;
                return 0;
            }

            VISITED[nextR][nextC] = true;
            DP[r][c] = Math.max(DP[r][c], play(nextR, nextC) + 1);
            VISITED[nextR][nextC] = false;
        }

        return DP[r][c];
    }
}
