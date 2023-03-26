package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 서울에서 경산까지
// https://www.acmicpc.net/problem/14863
public class N14863 {

    static int N, K;
    static long[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        K = input[1];
        DP = new long[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int walkTime = input[0];
            int walkValue = input[1];
            int bicycleTime = input[2];
            int bicycleValue = input[3];
            for (int j = 0; j <= K; j++) {
                DP[i][j] = Long.MIN_VALUE;  // 밑에서 update되지 않으면 도시에 도달할 수 없는 시간
                if (walkTime <= j) {
                    DP[i][j] = Math.max(DP[i][j], DP[i - 1][j - walkTime] + walkValue);
                }
                if (bicycleTime <= j) {
                    DP[i][j] = Math.max(DP[i][j], DP[i - 1][j - bicycleTime] + bicycleValue);
                }
            }
        }
        System.out.println(DP[N][K]);
    }
}
