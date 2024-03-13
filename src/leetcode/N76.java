package leetcode;

import java.util.HashMap;
import java.util.Map;

// Minimum Window Substring
public class N76 {
    public String minWindow(String s, String t) {
        Map<Character, Integer> alp = new HashMap<>();
        int count = 0;

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            alp.put(c, alp.getOrDefault(c, 0) + 1);
            count += 1;
        }

        int start = 0;
        int end = 0;
        while (end < s.length()) {
            char c = s.charAt(end);
            if (alp.containsKey(c) && alp.get(c) > 0) {
                alp.put(c, alp.get(c) - 1);
                count -= 1;
            }
            if (count == 0) {
                break;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

        }

        return "";
    }
}
