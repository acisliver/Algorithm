package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

// 경로 찾기
// https://www.acmicpc.net/problem/1513
public class N1513 {

    private static final int DIV = 1_000_007;

    static int N, M, C;
    static int[][] MAP = new int[51][51];
    static int[][][][] DP = new int[51][51][51][51];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        C = input[2];

        for (int i = 1; i <= C; i++) {
            input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            MAP[input[0]][input[1]] = i;
        }

        for (int i = 0; i < DP.length; i++) {
            for (int j = 0; j < DP[0].length; j++) {
                for (int k = 0; k < DP[0][0].length; k++) {
                    Arrays.fill(DP[i][j][k], -1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= C; i++) {
            sb.append(solve(1, 1, 0, i)).append(" ");
        }

        System.out.println(sb);
    }

    private static int solve(int r, int c, int prevC, int visitC) {

        if (visitC < 0) return 0;
        if (r > N || c > M) return 0;
        if (r == N && c == M) {
            if (visitC == 0 && MAP[r][c] == 0) return 1;
            if (visitC == 1 && MAP[r][c] > prevC) return 1;
            return 0;
        }

        if (DP[r][c][prevC][visitC] != -1) return DP[r][c][prevC][visitC];

        int result;

        if (MAP[r][c] == 0) {
            result = (solve(r + 1, c, prevC, visitC) + solve(r, c + 1, prevC, visitC)) % DIV;
            return DP[r][c][prevC][visitC] = result;
        }

        if (MAP[r][c] > prevC) {
            result = (solve(r + 1, c, MAP[r][c], visitC - 1) + solve(r, c + 1, MAP[r][c], visitC - 1)) % DIV;
            return DP[r][c][prevC][visitC] = result;
        }

        return DP[r][c][prevC][visitC] = 0;
    }
}
