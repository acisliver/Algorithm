package leetcode;

import java.util.HashMap;
import java.util.Map;

// Subarrays with K Different Integers
public class N992 {

    public int subarraysWithKDistinct(int[] nums, int k) {
        return solution(nums, k - 1) - solution(nums, k);
    }

    private int solution(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int answer = 0;
        int l = 0;
        int r = 0;
        while (r < nums.length) {
            if (map.containsKey(nums[r]) && map.get(nums[r]) > 0) {
                map.put(nums[r], map.get(nums[r]) + 1);
            } else {
                map.put(nums[r], 1);
                k -= 1;
            }
            r += 1;
            while (k < 0) {
                if (map.get(nums[l]) > 1) {
                    map.put(nums[l], map.get(nums[l]) - 1);
                } else {
                    k += 1;
                    map.put(nums[l], 0);
                }
                l += 1;
            }
            answer += l;
        }

        return answer;

    }
}
