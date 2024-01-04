package leetcode;

import java.util.HashMap;
import java.util.Map;

class N2870 {
    public int minOperations(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int answer = 0;
        for (int count : map.values()) {
            if (count == 1) {
                return -1;
            }
            if (count % 3 == 0) {
                answer += count / 3;
            } else {
                answer += count / 3 + 1;
            }
        }
        return answer;
    }
}
