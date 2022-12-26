package inflearn.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

// 알파벳 개수
// https://www.acmicpc.net/problem/10808
public class N10808 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] alphabet = new int[26];

        String input = br.readLine();

        input.chars()
                .forEach(i -> alphabet[i - 'a'] += 1);

        String answer = Arrays.stream(alphabet)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(answer);
    }
}
