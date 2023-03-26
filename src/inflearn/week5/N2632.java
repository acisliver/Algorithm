package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 피자 판매
// https://www.acmicpc.net/problem/2632
public class N2632 {

    static int SIZE, A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        SIZE = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        A = input[0];
        B = input[1];
        int[] a = new int[A];
        int[] b = new int[B];
        int[] aSum = new int[A * 2 + 1];
        int[] bSum = new int[B * 2 + 1];

        for (int i = 0; i < A; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < B; i++) {
            b[i] = Integer.parseInt(br.readLine());
        }

        sum(a, aSum, A);
        sum(b, bSum, B);

        Map<Integer, Integer> mapA = map(aSum, A);
        Map<Integer, Integer> mapB = map(bSum, B);

        int answer = 0;

        for (int pizzaA = 1; pizzaA < SIZE; pizzaA++) {
            answer += mapA.getOrDefault(pizzaA, 0) * mapB.getOrDefault(SIZE - pizzaA, 0);

        }

        answer += mapA.getOrDefault(SIZE, 0);
        answer += mapB.getOrDefault(SIZE, 0);

        System.out.println(answer);
    }

    private static void sum(int[] pizza, int[] sum, int size) {
        for (int i = 1; i <= size; i++) {
            sum[i] = sum[i - 1] + pizza[i - 1];
        }
        for (int i = size + 1; i <= 2 * size; i++) {
            sum[i] = sum[i - 1] + pizza[i - size - 1];
        }
    }

    private static Map<Integer, Integer> map(int[] sum, int size) {
        Map<Integer, Integer> map = new HashMap<>();

        int interval = 1;
        while (interval < size) {
            for (int start = interval; start < interval + size; start++) {
                int s = sum[start] - sum[start - interval];
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
            interval += 1;
        }
        int s = sum[size];
        map.put(s, map.getOrDefault(s, 0) + 1);

        return map;
    }
}
