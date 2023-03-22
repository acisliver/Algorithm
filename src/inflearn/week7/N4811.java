package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 알약
// https://www.acmicpc.net/problem/4811
public class N4811 {

    static int N;
    static long[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) return;
            DP = new long[2 * N + 1][N + 1];

            System.out.println(search(0, N));
//            System.out.println(Arrays.deepToString(DP));
        }
    }

    private static long search(int h, int w) {

        if (h == 0 && w == 0) return 1L;
        if (DP[h][w] > 0) return DP[h][w];

        long result = DP[h][w];

        // Whole
        if (w > 0) {
            result += search(h + 1, w - 1);
        }

        // Half
        if (h > 0) {
            result += search(h - 1, w);
        }

        DP[h][w] = result;
        return result;
    }
}
