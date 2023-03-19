package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 가장 긴 증가하는 부분 수열 2
// https://www.acmicpc.net/problem/12015
public class N12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] lis = new int[n];
        lis[0] = input[0];
        int len = 1;

        for (int i : input) {
            if (lis[len - 1] < i) {
                lis[len] = i;
                len += 1;
            } else {
                int idx = lowerBound(i, len, lis);
                lis[idx] = i;
            }
        }

        System.out.println(len);
    }

    private static int lowerBound(int key, int len, int[] arr) {

        int lo = -1;
        int hi = len;

        while (lo + 1 < hi) {
            int mid = (lo + hi) >>> 1;
            int midVal = arr[mid];

            if (midVal < key) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }
}
