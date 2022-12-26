package inflearn.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

// 일곱 난쟁이
// https://www.acmicpc.net/problem/2309
public class N2309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dwarves = new int[9];
        int totalTall = 0;

        for (int i = 0; i < 9; i++) {
            dwarves[i] = Integer.parseInt(br.readLine());
            totalTall += dwarves[i];
        }

        Arrays.sort(dwarves);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == j) continue;
                if (totalTall - dwarves[i] - dwarves[j] == 100) {
                    print(dwarves, i, j);
                    return;
                }
            }
        }
    }

    private static void print(int[] dwarves, int i, int j) {
        String output = Arrays.stream(dwarves)
                .filter(n -> n != dwarves[i])
                .filter(n -> n != dwarves[j])
                .mapToObj(String::valueOf)
                .collect(Collectors.joining("\n"));

        System.out.println(output);
    }
}
