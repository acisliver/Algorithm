package leetcode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/valid-anagram/
// Hash
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            char key = t.charAt(i);
            if (map.containsKey(key) && map.get(key) > 0) {
                map.put(key, map.get(key) - 1);
            } else {
                return false;
            }
        }

        return true;
    }
}
