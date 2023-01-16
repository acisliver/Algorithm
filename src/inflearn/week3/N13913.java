package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 숨바꼭질 4
// https://www.acmicpc.net/problem/13913
public class N13913 {
    static int catcher, hider;
    static int[][] dp = new int[500_000][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        catcher = input[0];
        hider = input[1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(catcher);
        dp[catcher] = new int[]{1, -1};

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == hider) break;

            if (cur + 1 < dp.length) {
                if (dp[cur + 1][0] == 0) {
                    queue.add(cur + 1);
                    dp[cur + 1] = new int[]{dp[cur][0] + 1, cur};
                }
            }
            if (cur - 1 >= 0) {
                if (dp[cur - 1][0] == 0) {
                    queue.add(cur - 1);
                    dp[cur - 1] = new int[]{dp[cur][0] + 1, cur};
                }
            }
            if (cur * 2 < dp.length) {
                if (dp[cur * 2][0] == 0) {
                    queue.add(cur * 2);
                    dp[cur * 2] = new int[]{dp[cur][0] + 1, cur};
                }

            }
        }


        System.out.println(dp[hider][0] - 1);

        Stack<Integer> stack = new Stack<>();
        stack.push(hider);

        while (true) {
            int cur = stack.peek();
            int next = dp[cur][1];
            if (next == -1) break;
            stack.push(next);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }
}
