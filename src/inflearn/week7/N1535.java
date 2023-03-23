package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 안녕
// https://www.acmicpc.net/problem/1535
public class N1535 {

    static int N;
    static int[] L, J;
    static int[][] DP = new int[21][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        L = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        J = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 1; i <= N; i++) {
            int life = L[i - 1];
            int joy = J[i - 1];
            for (int j = 0; j < 100; j++) {
                DP[i][j] = DP[i - 1][j];
                if (j >= life) {
                    DP[i][j] = Math.max(DP[i][j], DP[i - 1][j - life] + joy);
                }
            }
        }

        System.out.println(DP[N][99]);
    }
}
