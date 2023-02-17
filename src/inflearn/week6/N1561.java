package inflearn.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 놀이 공원
// https://www.acmicpc.net/problem/1561
/*
    시간복잡도
    N: 20억, M: 1만, 놀이기구 시간: 30분
    20억명이 30분짜리 놀이기구를 하나 타는 시간: 20억 * 30분 = 600억
    풀이
    이진탐색 파라미터: N명이 놀이기구를 타기 위한 시간(T)
    1. N명이 놀이기구를 타는 시간(T)을 계산한다.
    2. (T - 1) 시간까지 몇명(K)이 타는지 계산한다.
    3. T 시간에서 놀이기구를 새로 탈 수 있는 경우 계속 태운다.
    4. N번째 사람이 탔을 경우 타는 놀이기구가 정답
 */
public class N1561 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] input = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        long n = input[0];
        long m = input[1];
        int[] a = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        if (n <= m) {
            System.out.println(n);
            return;
        }

        long lo = -1;
        long hi = 60000000004L;

        while (lo + 1 < hi) {
            long mid = (lo + hi) >>> 1;

            if (check(mid, n, a)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        getAnswer(hi, n, a);
    }

    private static boolean check(long time, long n, int[] a) {

        long count = a.length;

        for (int i : a) {
            count += time / i;
        }

        return count >= n;
    }

    private static void getAnswer(long time, long n, int[] a) {

        long count = a.length;

        for (int i : a) {
            count += (time - 1) / i;
        }

        for (int i = 0; i < a.length; i++) {
            if (time % a[i] == 0) count += 1;
            if (count == n) {
                System.out.println(i + 1);
                return;
            }
        }
    }
}
