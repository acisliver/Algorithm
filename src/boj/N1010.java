package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

// 다리 놓기
// https://www.acmicpc.net/problem/1010
public class N1010 {

    static int[][][] DP = new int[31][31][31];

    static {
        for (int[][] ints : DP) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int n = input[0];
            int m = input[1];
            System.out.println(getCase(n, m, 0));
        }
    }

    private static int getCase(int n, int m, int prev) {
        if (n > m) return 0;
        if (n == m) return 1;
        if (n == 0) return 1;

        if (DP[n][m][prev] != -1) return DP[n][m][prev];

        int result = 0;
        for (int i = prev + 1; i <= m; i++) {
            result += getCase(n - 1, m, i);
        }

        return DP[n][m][prev] = result;
    }
}
