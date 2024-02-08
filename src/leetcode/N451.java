package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// Sort Characters By Frequency
public class N451 {
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        return s.chars()
                .mapToObj(i -> (char) i)
                .sorted((c1, c2) -> {
                    int c = map.get(c2) - map.get(c1);
                    if (c == 0) {
                        return c1 - c2;
                    }
                    return c;
                })
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
