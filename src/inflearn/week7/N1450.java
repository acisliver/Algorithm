package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 냅색문제
//https://www.acmicpc.net/problem/1450
public class N1450 {

    static int N, C;
    static int[] WEIGHTS;

    static int[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        C = input[1];
        WEIGHTS = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        DP = new int[N][C + 1];

        int answer = solve(0, 0);
        System.out.println(answer);
    }

    private static int solve(int n, int capacity) {
        if (n == N) {
            if (capacity > C) return 0;
            return 1;
        }

        if (DP[n][capacity] != 0) return DP[n][capacity];

        int weight = WEIGHTS[n];

        DP[n][capacity] += solve(n + 1, capacity + weight); // 넣기
        DP[n][capacity] += solve(n + 1, capacity);  // 넘어가기

        return DP[n][capacity];
    }
}
