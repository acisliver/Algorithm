package leetcode;

import java.util.*;
import java.util.stream.Collectors;

// Least Number of Unique Integers after K Removals
public class N1481 {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : arr) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = map.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .collect(Collectors.toList());

        for (Map.Entry<Integer, Integer> entry : list) {
            if (k >= entry.getValue()) {
                k -= entry.getValue();
                map.remove(entry.getKey());
            }
        }

        return map.size();
    }
}
