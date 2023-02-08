package inflearn.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

// 가장 긴 증가하는 부분 수열 4
// https://www.acmicpc.net/problem/14002
public class N14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int idx = 0;
        int max = 1;
        int[] dp = new int[n];
        int[] counts = new int[n];
        Arrays.fill(dp, -1);
        Arrays.fill(counts, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (seq[i] > seq[j] && counts[i] < counts[j] + 1) {
                    counts[i] = counts[j] + 1;
                    dp[i] = j;
                    if (max < counts[i]) {
                        max = counts[i];
                        idx = i;
                    }
                }
            }
        }

        System.out.println(max);
        Stack<Integer> stack = new Stack<>();
        while (idx != -1) {
            stack.push(seq[idx]);
            idx = dp[idx];
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }
}
