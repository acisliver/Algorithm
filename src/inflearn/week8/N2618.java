package inflearn.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 경찰차
// https://www.acmicpc.net/problem/2618
public class N2618 {

    static int N, W;
    static boolean[][] MAP;
    static int[][] CASES;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        MAP = new boolean[N + 1][N + 1];
        CASES = new int[W + 2][2];
        DP = new int[1004][1004];
        CASES[0] = new int[]{1, 1};
        CASES[1] = new int[]{N, N};
        for (int i = 2; i < W + 2; i++) {
            CASES[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        System.out.println(go(0, 1));
        trace();
    }

    private static int go(int a, int b) {
        if (a == W + 1 || b == W + 1) return 0;
        if (DP[a][b] != 0) return DP[a][b];
        int next = Math.max(a, b) + 1;
        DP[a][b] = Math.min(go(a, next) + getDistance(b, next), go(next, b) + getDistance(a, next));
        return DP[a][b];
    }

    private static int getDistance(int a, int b) {
        int[] p1 = CASES[a];
        int[] p2 = CASES[b];
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    private static void trace() {
        int a = 0;
        int b = 1;
        for (int i = 2; i < W + 2; i++) {
            if (DP[i][b] + getDistance(a, i) < DP[a][i] + getDistance(b, i)) {
                a = i;
                System.out.println(1);
            } else {
                b = i;
                System.out.println(2);
            }
        }
    }
}
