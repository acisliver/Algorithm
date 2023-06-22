package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import static java.lang.System.in;

// LCS 2
// https://www.acmicpc.net/problem/9252
public class N9252 {
    static int[][] DP;
    static char[] str1, str2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();
        DP = new int[str1.length + 1][str2.length + 1];

        System.out.println(LCS2(str1.length, str2.length));
        System.out.println(LCSstr2(str1.length, str2.length));
    }

    private static int LCS(int x, int y) {

        if (x == 0 || y == 0) return 0;

        if (DP[x][y] != 0) return DP[x][y];

        if (str1[x - 1] == str2[y - 1]) {
            return DP[x][y] = LCS(x - 1, y - 1) + 1;
        }

        return DP[x][y] = Math.max(LCS(x - 1, y), LCS(x, y -1));
    }

    private static int LCS2(int x, int y) {
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length ; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    DP[i][j] = DP[i - 1][j - 1] + 1;
                    continue;
                }
                DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
            }
        }
        return DP[x][y];
    }

    private static String LCSstr(int x, int y, Stack<Character> stack) {

        if (x == 0 || y == 0) {
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            return sb.toString();
        }

        int cur = DP[x][y];
        int up = DP[x - 1][y];
        int left = DP[x][y - 1];

        if (cur == up) {
            return LCSstr(x - 1, y, stack);
        }

        if (cur == left) {
            return LCSstr(x, y - 1, stack);
        }

        stack.push(str1[x - 1]);
        return LCSstr(x - 1, y - 1, stack);
    }

    private static String LCSstr2(int x, int y) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        while (x != 0 && y != 0) {
            int cur = DP[x][y];
            int up = DP[x - 1][y];
            int left = DP[x][y - 1];

            if (cur == up) {
                x -= 1;
                continue;
            }

            if (cur == left) {
                y -= 1;
                continue;
            }

            stack.push(str1[x - 1]);
            x -= 1;
            y -= 1;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
