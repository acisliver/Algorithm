package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 영화감독 숌
// https://www.acmicpc.net/problem/1436
public class N1436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String word = "666";

        int number = 665;
        while (n > 0) {
            number += 1;

            if (String.valueOf(number).contains(word)) {
                n -= 1;
            }
        }

        System.out.println(number);
    }
}
