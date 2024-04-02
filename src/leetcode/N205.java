package leetcode;

import java.util.HashMap;
import java.util.Map;

// Isomorphic Strings
public class N205 {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return  false;
        }

        Map<Character, Character> from = new HashMap<>();
        Map<Character, Character> to = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (from.containsKey(sc)) {
                if (from.get(sc) != tc) {
                    return false;
                }
            } else {
                if (to.containsKey(tc)) {
                    return false;
                }
                from.put(sc, tc);
                to.put(tc, sc);
            }
        }

        return true;
    }
}
