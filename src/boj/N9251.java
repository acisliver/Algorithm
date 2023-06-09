package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

// LCS
// https://www.acmicpc.net/problem/9251
public class N9251 {

    static int[][] DP;
    static char[] str1, str2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();
        DP = new int[1004][1004];
        for (int i = 0; i < 1004; i++) {
            Arrays.fill(DP[i], -1);
        }
        System.out.println(LCS(str1.length - 1, str2.length - 1));
    }

    private static int LCS(int x, int y) {

        if (x < 0 || y < 0) return 0;

        if (DP[x][y] != -1) return DP[x][y];

        if (str1[x] == str2[y]) {
            return DP[x][y] = LCS(x - 1, y - 1) + 1;
        }

        return DP[x][y] = Math.max(LCS(x - 1, y), LCS(x, y -1));
    }
}
