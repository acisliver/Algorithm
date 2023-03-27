package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 동전 2
// https://www.acmicpc.net/problem/2294
public class N2294 {

    static final int MAX = 123456789;

    static int N, K;
    static int[] VALUES;
    static int[][] DP = new int[104][100_004];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        K = input[1];
        DP = new int[N + 1][K + 1];
        VALUES = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            VALUES[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= K; i++) {
            DP[0][i] = MAX;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                DP[i][j] = DP[i - 1][j];
                if (j >= VALUES[i]) {
                    // 현재 동전을 한 번 더 쓰는 경우
                    DP[i][j] = Math.min(DP[i][j], DP[i][j - VALUES[i]] + 1);
                    // 이전 경우에서 현재 동전을 쓰는 경우
                    DP[i][j] = Math.min(DP[i][j], DP[i - 1][j - VALUES[i]] + 1);
                }
            }
        }

        System.out.println(DP[N][K] >= MAX ? - 1 : DP[N][K]);
    }

}
