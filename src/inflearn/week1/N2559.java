package inflearn.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 수열
// https://www.acmicpc.net/problem/2559
public class N2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        int[] sequence = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] prefixSum = new int[n + 1];
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < sequence.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + sequence[i];
        }

        for (int i = k; i < prefixSum.length; i++) {
            int sum = prefixSum[i] - prefixSum[i - k];
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}
