package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 사다리 조작
// https://www.acmicpc.net/problem/15684
public class N15684 {

    static int N, M, H, ANSWER;
    static boolean[][] LADDERS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        H = input[2];
        ANSWER = 4;
        LADDERS = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int row = input[0];
            int col = input[1];
            LADDERS[row][col] = true;
        }

        solve(3);

        System.out.println(ANSWER <= 3 ? ANSWER : -1);
    }

    private static boolean checkLadders(boolean[][] ladders) {
        for (int i = 1; i <= N; i++) {
            int start = i;
            for (int j = 1; j <= H; j++) {
                if (ladders[j][start]) {
                    start += 1;
                } else if (ladders[j][start - 1]) {
                    start -= 1;
                }
            }
            if (start != i) return false;
        }

        return true;
    }

    private static void solve(int ladder) {
        if (checkLadders(LADDERS)) {
            ANSWER = Math.min(ANSWER, 3 - ladder);
            return;
        }

        if (ladder == 0) {
            return;
        }

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (LADDERS[i][j]) continue;
                if (LADDERS[i][j - 1] || LADDERS[i][j + 1]) continue;

                LADDERS[i][j] = true;
                solve(ladder - 1);
                LADDERS[i][j] = false;
            }
        }
    }
}
