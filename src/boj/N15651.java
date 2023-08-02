package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.in;

// N과 M (3)
// https://www.acmicpc.net/problem/15651
public class N15651 {

    static int N, M;
    static StringBuilder ANSWER = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int[] ints = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = ints[0];
        M = ints[1];
        permutation(0, new int[M]);
        System.out.println(ANSWER);
    }

    private static void permutation(int index, int[] arr) {
        if (index == M) {
            String answer = Arrays.stream(arr)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
            ANSWER.append(answer)
                    .append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[index] = i + 1;
            permutation(index + 1, arr);
        }
    }
}
