package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.in;

// 모든 순열
// https://www.acmicpc.net/problem/10974
public class N10974 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        N = Integer.parseInt(br.readLine());

        solve(0, new boolean[N], new int[N]);
    }

    private static void solve(int index, boolean[] visited, int[] arr) {
        if (index == N) {
            String s = Arrays.stream(arr)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
            System.out.println(s);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            arr[index] = i + 1;
            solve(index + 1, visited, arr);
            visited[i] = false;
        }
    }
}
