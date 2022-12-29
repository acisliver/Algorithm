package inflearn.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 팰린드롬 만들기
// https://www.acmicpc.net/problem/1213
public class N1213 {

    private static final String NO = "I'm Sorry Hansoo";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] alphabet = new int[26];
        char[] word = br.readLine().toCharArray();
        List<String> prefix = new ArrayList<>();
        LinkedList<String> suffix = new LinkedList<>();

        for (char c : word) {
            alphabet[c - 'A'] += 1;
        }

        String mid = "";

        for (int i = 0; i < alphabet.length; i++) {
            int count = alphabet[i];
            if (count > 0) {
                String s = String.valueOf((char) (i + 'A'));
                if (count % 2 == 0) {
                    for (int j = 0; j < count / 2; j++) {
                        prefix.add(s);
                        suffix.addFirst(s);
                    }
                } else {
                    if (mid.equals("")) {
                        mid = s;
                        for (int j = 0; j < (count - 1) / 2; j++) {
                            prefix.add(s);
                            suffix.addFirst(s);
                        }
                    } else {
                        System.out.println(NO);
                        return;
                    }
                }
            }
        }

        prefix.add(mid);
        prefix.addAll(suffix);

        String palindrome = String.join("", prefix);

        System.out.println(palindrome);
    }
}
