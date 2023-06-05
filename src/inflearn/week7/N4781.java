package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 사탕 가게
// https://www.acmicpc.net/problem/4781
public class N4781 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = br.readLine();
            String[] split = input.split(" ");
            if (input.equals("0 0.00")) return;

            N = Integer.parseInt(split[0]);
            M = Integer.parseInt(split[1].replace(".", ""));
            int[][] DP = new int[N + 1][M + 1];
            for (int candy = 1; candy <= N; candy++) {
                split = br.readLine().split(" ");
                int cal = Integer.parseInt(split[0]);
                int price = Integer.parseInt(split[1].replace(".", ""));
                for (int money = 1; money <= M; money++) {
                    DP[candy][money] = DP[candy - 1][money];
                    if (money < price) continue;
                    DP[candy][money] = Math.max(cal + DP[candy - 1][money - price], DP[candy][money]);
                    DP[candy][money] = Math.max(cal + DP[candy][money - price], DP[candy][money]);  // 같은 사탕 여러번 구매 가능
                }
            }
            System.out.println(DP[N][M]);
        }
    }
}
