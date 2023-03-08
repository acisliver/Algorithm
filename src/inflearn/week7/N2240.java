package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 자두나무
// https://www.acmicpc.net/problem/2240
public class N2240 {

    static int DP[][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int T = input[0];
        int W = input[1];
        DP = new int[T][2][W];
    }
}
