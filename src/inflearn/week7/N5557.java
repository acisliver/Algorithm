package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 1학년
// https://www.acmicpc.net/problem/5557
public class N5557 {

    static int N, GOAL;
    static int[] NUMBERS;
    static long[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        NUMBERS = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        GOAL = NUMBERS[N - 1];
        DP = new long[N - 1][21];
        for (int i = 0; i < N - 1; i++) {
            Arrays.fill(DP[i], -1);
        }
//        DP[0][NUMBERS[0]] = 1;
        long answer = go(N - 2, GOAL);
        System.out.println(answer < 0 ? 0 : answer);
    }

    private static long go(int idx, int value) {
        if (value > 20 || value < 0) return 0;

        if (idx == 0) {
            if (value == NUMBERS[0]) return 1;
            return 0;
        }

        if (DP[idx][value] > -1) return DP[idx][value];

        DP[idx][value] = go(idx - 1, value + NUMBERS[idx]) + go(idx - 1, value - NUMBERS[idx]);
        return DP[idx][value];
    }
}
