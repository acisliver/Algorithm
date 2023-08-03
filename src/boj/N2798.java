package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

// 블랙잭
// https://www.acmicpc.net/problem/2798
public class N2798 {

    static int N, M, ANSWER;
    static int[] ARRAY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int[] ints = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = ints[0];
        M = ints[1];
        ANSWER = 0;
        ARRAY = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        solve(0, 0, new boolean[N]);

        System.out.println(ANSWER);
    }

    private static void solve(int count, int sum, boolean[] visited) {
        if (sum > M) return;

        if (count == 3) {
            ANSWER = Math.max(ANSWER, sum);
            return;
        }

        for (int i = 0; i < ARRAY.length; i++) {
            if (visited[i]) continue;
            int number = ARRAY[i];
            visited[i] = true;
            solve(count + 1, sum + number, visited);
            visited[i] = false;
        }
    }
}
