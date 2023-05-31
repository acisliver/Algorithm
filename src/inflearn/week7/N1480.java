package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 보석 모으기
// https://www.acmicpc.net/problem/1480
public class N1480 {

    static int N, M, C;
    static int[] JEWELS;
    static int[][][] DP = new int[11][21][1 << 13];  // bag, weight, visited

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        C = input[2];
        JEWELS = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        // 방문 안함으로 초기화
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 21; j++) {
                Arrays.fill(DP[i][j], -1);
            }
        }

        int answer = solve(0, 0, 0);
        System.out.println(answer);
    }

    private static int solve(int bag, int weight, int visited) {
        if (bag == M) return -1;
        if (visited == (1 << N) - 1) return 0;

        if (DP[bag][weight][visited] != -1) return DP[bag][weight][visited];

        DP[bag][weight][visited] = 0;
        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) == (1 << i)) continue;
            if (JEWELS[i] > C) continue;

            if (weight + JEWELS[i] > C) {   // 다음 가방에 넣는 경우
                DP[bag][weight][visited] = Math.max(DP[bag][weight][visited], solve(bag + 1, JEWELS[i], (visited | (1 << i))) + 1);
            } else {    // 현재 가방에 넣는 경우
                DP[bag][weight][visited] = Math.max(DP[bag][weight][visited], solve(bag, weight + JEWELS[i], (visited | (1 << i))) + 1);
            }
        }

        return DP[bag][weight][visited];
    }

}
