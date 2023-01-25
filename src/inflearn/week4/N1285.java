package inflearn.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 동전 뒤집기
// https://www.acmicpc.net/problem/1285
public class N1285 {

    static int N, MIN_HEAD;
    static int[] ROW_COINS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        MIN_HEAD = Integer.MAX_VALUE;
        ROW_COINS = new int[N];

        for (int i = 0; i < N; i++) {
            char[] coins = br.readLine().toCharArray();
            int value = (int) Math.pow(2, 0);
            for (int j = 0; j < N; j++) {
                if (coins[j] == 'H') ROW_COINS[i] |= value;
                value *= 2;
            }
        }

        reverseCoins(0);

        System.out.println(MIN_HEAD);
    }

    private static void reverseCoins(int here) {
        
        if (here == N) {
            int headCount = 0;
            for (int i = 0; i < N; i++) {
                int count = 0;
                int col = (int) Math.pow(2, i);
                for (int j = 0; j < N; j++) {
                    if ((ROW_COINS[j] & col) != 0) count += 1;
                }
                headCount += Math.min(count, N - count);    // H의 개수가 만으면 column을 뒤집은 개수를 사용
            }

            MIN_HEAD = Math.min(MIN_HEAD, headCount);
            return;
        }

        reverseCoins(here + 1);
        ROW_COINS[here] = ~ROW_COINS[here];
        reverseCoins(here + 1);
    }
}
