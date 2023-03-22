package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 동전 1
// https://www.acmicpc.net/problem/2293
public class N2293 {

    static int N, K;
    static int[] DP;
    static int[] COINS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        K = input[1];
        DP = new int[K + 1];
        COINS = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            COINS[i] = Integer.parseInt(br.readLine());
        }

        DP[0] = 1;

        for (int i = 1; i <= N; i++) {
            int coin = COINS[i];
            for (int j = coin; j <= K; j++) {
                DP[j] += DP[j - coin];
            }
        }

        System.out.println(DP[K]);
        System.out.println(Arrays.toString(DP));
    }

}
