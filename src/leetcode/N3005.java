package leetcode;

import java.util.HashMap;
import java.util.Map;

// Count Elements With Maximum Frequency
public class N3005 {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int answer = 0;
        int max = -1;
        for (Integer value : map.values()) {
            if (max == value) {
                answer += value;
            } else if (max < value) {
                max = value;
                answer = value;
            }
        }

        return answer;
    }
}
