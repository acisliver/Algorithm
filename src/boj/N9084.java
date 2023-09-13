package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

// 동전
// https://www.acmicpc.net/problem/9084
public class N9084 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int M = Integer.parseInt(br.readLine());
            int count = getCount(M, coins);
            System.out.println(count);
        }
    }

    private static int getCount(int money, int[] coins) {
        int[] memo = new int[money + 1];
        memo[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= money; i++) {
                memo[i] += memo[i - coin];
            }
        }

        return memo[money];
    }
}
