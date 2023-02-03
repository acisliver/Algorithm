package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 부분합
// https://www.acmicpc.net/problem/1806
public class N1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0];
        int s = input[1];
        int minLen = Integer.MAX_VALUE;
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int start = 0;
        int end = 0;
        int sum = 0;

        while (true) {
            if (sum >= s) {
                sum -= arr[start];
                minLen = Math.min(end - start, minLen);
                start += 1;
            } else if (end == n) {
                break;
            } else {
                sum += arr[end];
                end += 1;
            }
        }

        System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
    }
}
