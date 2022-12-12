package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 행렬 곱셈 순서
// https://www.acmicpc.net/problem/11049
public class N11049 {

    static int N;
    static int[][] MATRIX, DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        MATRIX = new int[N][];

        for (int i = 0; i < N; i++) {
            MATRIX[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        DP = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(DP[i], Integer.MAX_VALUE);
        }
        int answer = topDownSolution(0, N - 1);
        System.out.println(answer);

        int answer2 = bottomUpSolution();
        System.out.println(answer2);
    }

    // 메모이제이션
    // 분할정복(재귀함수): 큰 문제 해결을 위해 작은 문제를 호출
    private static int topDownSolution(int from, int to) {
        if (from == to) {
            return 0;
        }

        if (DP[from][to] != Integer.MAX_VALUE) {
            return DP[from][to];
        }

        for (int i = from; i < to; i++) {
            int curCount = MATRIX[from][0] * MATRIX[i][1] * MATRIX[to][1];
            int count = topDownSolution(from, i) + topDownSolution(i + 1, to) + curCount;
            DP[from][to] = Math.min(DP[from][to], count);
        }

        return DP[from][to];
    }

    // DP 테이블
    // 반복문: 작은 문제부터 해결
    private static int bottomUpSolution() {
        int[][] dp = new int[N][N];

        for (int gap = 1; gap < N; gap++) {
            for (int from = 0; from < N - gap; from++) {
                dp[from][from + gap] = Integer.MAX_VALUE;
                for (int mid = from; mid < from + gap; mid++) {
                    int curCount = MATRIX[from][0] * MATRIX[mid][1] * MATRIX[from + gap][1];
                    int count = dp[from][mid] + dp[mid + 1][from + gap] + curCount;
                    dp[from][from + gap] = Math.min(dp[from][from + gap], count);
                }
            }
        }

        return dp[0][N - 1];
    }

}
