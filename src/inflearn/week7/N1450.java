package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 냅색문제
//https://www.acmicpc.net/problem/1450
public class N1450 {

    static int N, C;
    static int[] WEIGHTS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        C = input[1];
        WEIGHTS = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int answer = solve();
        System.out.println(answer);
    }

    private static int solve() {
        int answer = 0;

        List<Integer> combination1 = new ArrayList<>();
        List<Integer> combination2 = new ArrayList<>();

        go(0, N / 2 - 1, 0, combination1);
        go(N / 2, N - 1, 0, combination2);

        Collections.sort(combination1);
        Collections.sort(combination2);

        for (Integer sum1 : combination1) {
            int left = -1;
            int right = combination2.size();
            while (left + 1 < right) {
                int mid = (left + right) >>> 1;
                int midVal= combination2.get(mid);
                if (sum1 + midVal <= C) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            answer += left + 1;
        }

        return answer;
    }

    private static void go(int cur, int end, int sum, List<Integer> combination) {
        if (sum > C) return;
        if (cur > end) {
            combination.add(sum);
            return;
        }

        go(cur + 1, end, sum, combination);
        go(cur + 1, end, sum + WEIGHTS[cur], combination);
    }

}
