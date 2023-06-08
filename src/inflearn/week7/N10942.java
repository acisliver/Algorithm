package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

// 팰린드롬?
// https://www.acmicpc.net/problem/10942
public class N10942 {
    static int[][] DP = new int[2001][2001];
    static int N, M;
    static int[] ARRAY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        N = Integer.parseInt(br.readLine());
        ARRAY = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int[] query = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            sb.append(solve(query[0] - 1, query[1] - 1)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int startInclusive, int endInclusive) {
        if (startInclusive > endInclusive) return 0;

        if (startInclusive == endInclusive) return 1;
        if (endInclusive - startInclusive == 1) {
            return ARRAY[startInclusive] == ARRAY[endInclusive] ? 1 : 0;
        }

        if (DP[startInclusive][endInclusive] == 1) return DP[startInclusive][endInclusive];

        int result = ARRAY[startInclusive] == ARRAY[endInclusive] ? 1 : 0;

        if (result == 0) {
            return DP[startInclusive][endInclusive] = result;
        }

        int inner =  solve(startInclusive + 1, endInclusive - 1);

        if (inner == result) {
            return DP[startInclusive][endInclusive] = result;
        }

        return DP[startInclusive][endInclusive] = 0;
    }

}
