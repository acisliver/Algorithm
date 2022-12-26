package inflearn.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 농구 경기
// https://www.acmicpc.net/problem/1159
public class N1159 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] lastNames = new int[26];
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            lastNames[br.readLine().charAt(0) - 'a'] += 1;
        }

        for (int i = 0; i < lastNames.length; i++) {
            if (lastNames[i] >= 5) {
                sb.append((char) (i + 'a'));
            }
        }

        System.out.println(sb.toString().length() == 0 ? "PREDAJA" : sb);
    }
}
