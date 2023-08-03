package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

// 암호 만들기
// https://www.acmicpc.net/problem/1759
public class N1759 {

    static int L, C;
    static char[] CANDIDATES;
    static List<char[]> ANSWERS = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        String[] chars = br.readLine().split(" ");
        L = input[0];
        C = input[1];
        CANDIDATES = new char[C];
        for (int i = 0; i < chars.length; i++) {
            CANDIDATES[i] = chars[i].charAt(0);
        }

        Arrays.sort(CANDIDATES);

        solve(0, new char[L], new boolean[C], 0);

        List<Character> l = new ArrayList<>();

        ANSWERS.stream()
                .filter(N1759::isValid)
                .forEach(System.out::println);
    }

    private static void solve(int index, char[] key, boolean[] visited, int startIndex) {
        if (index == L) {
            ANSWERS.add(Arrays.copyOf(key, key.length));
            return;
        }

        for (int i = startIndex, candidatesLength = CANDIDATES.length; i < candidatesLength; i++) {
            if (visited[i]) continue;
            char candidate = CANDIDATES[i];
            key[index] = candidate;
            visited[i] = true;
            solve(index + 1, key, visited, i + 1);
            visited[i] = false;
        }
    }

    private static boolean isValid(char[] key) {
        int vowelCount = 0;
        int consonantCount = 0;

        for (char k : key) {
            if (isVowel(k)) {
                vowelCount += 1;
            } else {
                consonantCount += 1;
            }
        }

        if (vowelCount < 1) return false;
        if (consonantCount < 2) return false;

        return true;
    }

    private static boolean isVowel(char c) {
        final char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        for (char vowel : vowels) {
            if (c == vowel) return true;
        }

        return false;
    }
}
