package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 숨바꼭질 2
// https://www.acmicpc.net/problem/12851
public class N12851 {

    static int catcher, hider;
    static int[] dp = new int[500_000];
    static int[] count = new int[500_000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        catcher = input[0];
        hider = input[1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(catcher);
        dp[catcher] = 1;
        count[catcher] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur + 1 < dp.length) {
                if (dp[cur + 1] == 0 || dp[cur + 1] == dp[cur] + 1) {
                    queue.add(cur + 1);
                    dp[cur + 1] = dp[cur] + 1;
                    count[cur + 1] += 1;
                }
            }
            if (cur - 1 >= 0) {
                if (dp[cur - 1] == 0 || dp[cur - 1] == dp[cur] + 1) {
                    queue.add(cur - 1);
                    dp[cur - 1] = dp[cur] + 1;
                    count[cur - 1] += 1;
                }
            }
            if (cur * 2 < dp.length) {
                if (dp[cur * 2] == 0 || dp[cur * 2] == dp[cur] + 1) {
                    queue.add(cur * 2);
                    dp[cur * 2] = dp[cur] + 1;
                    count[cur * 2] += 1;
                }

            }
        }

        System.out.println(dp[hider] - 1);
        System.out.println(count[hider]);
    }

}
