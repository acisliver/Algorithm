package inflearn.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 게임
// https://www.acmicpc.net/problem/1072
public class N1072 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] input = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        long x = input[0];
        long y = input[1];
        long winRate = 100 * y / x;
        if (winRate == 100) {
            System.out.println(-1);
            return;
        }

        long lo = -1;
        long hi = 10_000_000_000L;

        while (lo + 1 < hi) {
            long mid = (lo + hi) >>> 1;

            if (check(mid, x, y)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        if (hi >= 10_000_000_000L) {
            System.out.println(-1);
        } else {
            System.out.println(hi);
        }
    }

    private static boolean check(long n, long x, long y) {
        long prev = 100 * y / x;
        long cur = 100 * (y + n) / (x + n);
        return prev < cur;
    }
}
