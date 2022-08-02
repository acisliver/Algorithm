package leetcode;

import java.util.*;

// https://leetcode.com/problems/find-all-anagrams-in-a-string/
// Sliding Window
public class N438 {
    public static void main(String[] args) {
        System.out.println(new N438().findAnagrams("cbaebabacd", "abc"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() == 0 || p.length() == 0 || s.length() < p.length()) return new ArrayList<>();

        List<Integer> answer = new LinkedList<>();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {
            char key = p.charAt(i);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int sLen = s.length();
        int pLen = p.length();

        for (int i = 0; i <= sLen - pLen; i++) {
            int start = i;
            boolean isAnagram = true;
            for (int j = 0; j < pLen; j++) {
                char c = s.charAt(start);
                if (map.containsKey(c) && map.get(c) > 0) {
                    map.put(c, map.get(c) - 1);
                    start++;
                } else {
                    isAnagram = false;
                    start -= 1;
                    while(start >= i) {
                        c = s.charAt(start);
                        map.put(c, map.get(c) + 1);
                        start--;
                    }
                    break;
                }
            }

            for (Integer value : map.values()) {
                if (value > 0) {
                    isAnagram = false;
                    break;
                }
            }

            start -= 1;
            while (start >= i) {
                char c = s.charAt(start);
                map.put(c, map.get(c) + 1);
                start--;
            }

            if (isAnagram) answer.add(i);
        }



        return answer;
    }
}
