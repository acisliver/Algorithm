package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 앱
// https://www.acmicpc.net/problem/7579
public class N7579 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[] memorySizes = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] costs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maxCost = Arrays.stream(costs)  // 발생할 수 있는 최대 비용
                .reduce(Integer::sum)
                .orElseThrow();

        int[][] dp = new int[N + 1][maxCost + 1];   // 비용을 사용하여 얻을 수 있는 메모리
        int answer = maxCost;               // 최소 비용

        for (int i = 1; i < N + 1; i++) {   // i번째 앱을 종료시키려 함

            int cost = costs[i - 1];
            int memory = memorySizes[i - 1];

            for (int j = 1; j < maxCost + 1; j++) { // j비용을 사용할 수 있음
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);    // 앱을 종료하지 않는 경우

                if (j - cost < 0) continue;     // 앱을 종료할 수 있다면

                // 앱을 종료하지 않은 경우와 앱을 종료하는 경우 중 얻는 메모리가 큰 경우 사용
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost] + memory);

                // 만약 요구 메모리를 충족했다면 j비용으로 비용 최솟값 초기화
                if (dp[i][j] >= M) {
                    answer = Math.min(answer, j);
                }
            }
        }

        System.out.println(answer);
    }
}
