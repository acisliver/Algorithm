import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int n = Integer.parseInt(br.readLine());
        int[] ints = new int[n + 1];
        int[] dp = new int[n + 1];
        int max = 0;
        String[] input = br.readLine().split(" ");
        for (int i = 1; i < n + 1; i++) {
            ints[i]  = Integer.parseInt(input[i - 1]);
        }

        for (int i = 1; i < ints.length; i++) {
            for (int j = 0; j < i; j++) {
                if (ints[i] > ints[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        System.out.println(max);
    }
}
