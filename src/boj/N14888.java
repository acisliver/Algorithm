package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static java.lang.System.in;

// 연산자 끼워넣기
// https://www.acmicpc.net/problem/14888
public class N14888 {

    static int N, MAX, MIN;
    static int[] ARR, OP_SYMBOLS;
    static final List<BiFunction<Integer, Integer, Integer>> OPERATIONS = List.of(
            (x, y) -> x + y,
            (x, y) -> x - y,
            (x, y) -> x * y,
            (x, y) -> x / y
    );

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        N = Integer.parseInt(br.readLine());
        MAX = Integer.MIN_VALUE;
        MIN = Integer.MAX_VALUE;
        ARR = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        OP_SYMBOLS = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        solve(ARR[0], 1);

        System.out.println(MAX);
        System.out.println(MIN);
    }

    private static void solve(int temp, int index) {
        if (index == N) {
            MAX = Math.max(temp, MAX);
            MIN = Math.min(temp, MIN);
            return;
        }

        int target = ARR[index];
        for (int i = 0; i < 4; i++) {
            if (OP_SYMBOLS[i] == 0) continue;

            var operation = OPERATIONS.get(i);
            OP_SYMBOLS[i] -= 1;
            solve(operation.apply(temp, target), index + 1);
            OP_SYMBOLS[i] += 1;
        }
    }
}
