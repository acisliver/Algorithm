package leetcode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/ransom-note/
// Hash
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < magazine.length(); i++) {
            char key = magazine.charAt(i);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char key = ransomNote.charAt(i);
            if (map.containsKey(key) && map.get(key) > 0) {
                map.put(key, map.get(key) - 1);
            } else {
                return false;
            }
        }

        return true;
    }
}
