package leetcode;

import java.util.HashMap;
import java.util.Map;

// Custom Sort String
public class N791 {
    public String customSortString(String order, String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        StringBuilder answer = new StringBuilder();
        for (char c : order.toCharArray()) {
            int iter = map.getOrDefault(c, 0);
            for (int i = 0; i < iter; i++) {
                answer.append(c);
            }
            if (iter > 0) {
                map.remove(c);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int iter = entry.getValue();
            for (int i = 0; i < iter; i++) {
                answer.append(entry.getKey());
            }
        }

        return answer.toString();
    }
}
