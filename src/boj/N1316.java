package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

// 그룹 단어 체커
// https://www.acmicpc.net/problem/1316
public class N1316 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int n = Integer.parseInt(br.readLine());
        int answer = 0;
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            if (isGroupWord(word)) {
                answer += 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean isGroupWord(String word) {
        if (word.length() == 0) {
            return true;
        }

        boolean[] alphabetMarker = new boolean[26];
        char prev = word.charAt(0);
        for (int i = 1; i < word.length(); i++) {
            char cur = word.charAt(i);
            if (prev == cur) {
                continue;
            }
            if (alphabetMarker[prev - 'a']) {
                return false;
            }
            alphabetMarker[prev - 'a'] = true;
            prev = cur;
        }

        if (alphabetMarker[prev - 'a']) {
            return false;
        }

        return true;
    }
}
