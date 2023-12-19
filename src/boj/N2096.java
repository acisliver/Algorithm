package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 내려가기
// https://www.acmicpc.net/problem/2096
public class N2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] nums = new int[n][3];
        int[][] min = new int[n][3];
        int[][] max = new int[n][3];

        for (int i = 0; i < n; i++) {
            nums[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        min[0] = nums[0];
        max[0] = nums[0];

        if (n == 1) {
            int resultMin = Math.min(min[n - 1][0], Math.min(min[n - 1][1], min[n - 1][2]));
            int resultMax = Math.max(max[n - 1][0], Math.max(max[n - 1][1], max[n - 1][2]));

            System.out.printf("%d %d", resultMax, resultMin);
            return;
        }

        min[1][0] = nums[1][0] + Math.min(min[0][0], min[0][1]);
        min[1][1] = nums[1][1] + Math.min(min[0][2], Math.min(min[0][0], min[0][1]));
        min[1][2] = nums[1][2] + Math.min(min[0][1], min[0][2]);

        max[1][0] = nums[1][0] + Math.max(max[0][0], max[0][1]);
        max[1][1] = nums[1][1] + Math.max(max[0][2], Math.max(max[0][0], max[0][1]));
        max[1][2] = nums[1][2] + Math.max(max[0][1], max[0][2]);

        if (n == 2) {
            int resultMin = Math.min(min[n - 1][0], Math.min(min[n - 1][1], min[n - 1][2]));
            int resultMax = Math.max(max[n - 1][0], Math.max(max[n - 1][1], max[n - 1][2]));

            System.out.printf("%d %d", resultMax, resultMin);
            return;
        }

        for (int i = 2; i < n; i++) {
            // x 1 1
            // x 2 1
            min[i][0] = Math.min(min[i - 1][0], min[i - 1][1]) + nums[i][0];

            // x 1 2
            // x 2 2
            // x 3 2
            min[i][1] = Math.min(min[i - 1][0], Math.min(min[i - 1][1], min[i - 1][2])) + nums[i][1];

            // x 2 3
            // x 3 3
            min[i][2] = Math.min(min[i - 1][1], min[i - 1][2]) + nums[i][2];

            // x 1 1
            // x 2 1
            max[i][0] = Math.max(max[i - 1][0], max[i - 1][1]) + nums[i][0];

            // x 1 2
            // x 2 2
            // x 3 2
            max[i][1] = Math.max(max[i - 1][0], Math.max(max[i - 1][1], max[i - 1][2])) + nums[i][1];

            // x 2 3
            // x 3 3
            max[i][2] = Math.max(max[i - 1][1], max[i - 1][2]) + nums[i][2];
        }

        int resultMin = Math.min(min[n - 1][0], Math.min(min[n - 1][1], min[n - 1][2]));
        int resultMax = Math.max(max[n - 1][0], Math.max(max[n - 1][1], max[n - 1][2]));

        System.out.printf("%d %d", resultMax, resultMin);
    }
}
