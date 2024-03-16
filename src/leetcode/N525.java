package leetcode;

import java.util.HashMap;
import java.util.Map;

// Contiguous Array
public class N525 {
    public int findMaxLength(int[] nums) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int answer = 0;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i] == 1 ? 1 : -1;
            sum += val;
            if (map.containsKey(sum)) {
                answer = Math.max(answer, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }

        return answer;
    }
}
