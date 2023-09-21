package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.System.in;

// 가장 큰 정사각형
// https://www.acmicpc.net/problem/1915
public class N1915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int[] ints = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = ints[0];
        int m = ints[1];
        int[][] dp = new int[n + 1][m + 1];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                if (row[j].equals("0")) {
                    continue;
                }
                dp[i + 1][j + 1] = min(dp[i][j], dp[i + 1][j], dp[i][j + 1]) + 1;
                answer = Math.max(dp[i + 1][j + 1], answer);
            }
        }

        System.out.println(answer * answer);
    }

    private static int min(int... ints) {
        int result = Integer.MAX_VALUE;
        for (int i : ints) {
            result = Math.min(result, i);
        }
        return result;
    }

    private static int minV2(int... ints) {
        return Arrays.stream(ints)
                .min()
                .orElseThrow();
    }

    private static int minV3(int a, int b, int c) {
        return Stream.of(a, b, c)
                .min(Integer::compareTo)
                .orElseThrow();
    }

}
