package leetcode;

import java.util.Stack;

// Minimum Number of Steps to Make Two Strings Anagram
public class N1347 {
    public int minSteps(String s, String t) {
        int[] ss = new int[26];
        int[] tt = new int[26];

        for (int i = 0; i < s.length(); i++) {
            ss[s.charAt(i) - 'a'] += 1;
        }

        for (int i = 0; i < t.length(); i++) {
            tt[t.charAt(i) - 'a'] += 1;
        }

        int answer = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < ss.length; i++) {
            int sc = ss[i];
            int tc = tt[i];

            if (sc == tc) {
                continue;
            }

            int offset = Math.abs(sc - tc);
            while (!stack.isEmpty() && offset > 0) {
                stack.pop();
                offset -= 1;
                answer += 1;
            }

            while (offset > 0) {
                stack.push('a');
                offset -= 1;
            }
        }

        return answer + stack.size() / 2;
    }
}
