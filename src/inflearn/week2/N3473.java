package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 교수가 된 현우
// https://www.acmicpc.net/problem/3473
public class N3473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            System.out.println(getZeros(Integer.parseInt(br.readLine())));
        }
    }

    private static int getZeros(int number) {
        int twos = 0;
        int fives = 0;

        for (int i = 2; i <= number; i *= 2) {
            twos += number / i;
        }

        for (int i = 5; i <= number; i *= 5) {
            fives += number / i;
        }

        return Math.min(twos, fives);
    }
}
