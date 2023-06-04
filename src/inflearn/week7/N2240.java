package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 자두나무
// https://www.acmicpc.net/problem/2240
public class N2240 {

    static int T, W;
    static int[] APPLES;
    static int DP[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        T = input[0];
        W = input[1];
        APPLES = new int[T];
        for (int i = 0; i < T; i++) {
            APPLES[i] = Integer.parseInt(br.readLine());
        }
        DP = new int[T][W + 1];
        for (int i = 0; i < T; i++) {
            Arrays.fill(DP[i], -1);
        }

        int answer = solve(0, W, 1);
        System.out.println(answer);
    }

    private static int solve(int time, int move, int pos) {
        if (time == T) {
            return 0;
        }

        if (DP[time][move] != -1) return DP[time][move];

        int applePos = APPLES[time];
        if (applePos == pos) {
            DP[time][move] = Math.max(DP[time][move], solve(time + 1, move, pos) + 1);
        } else {
            if (move >  0) {
                DP[time][move] = Math.max(DP[time][move], solve(time + 1, move - 1, applePos) + 1);
            }
        }
        DP[time][move] = Math.max(DP[time][move], solve(time + 1, move, pos));

        return DP[time][move];
    }
}
