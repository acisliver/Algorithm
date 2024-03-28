package leetcode;

import java.util.HashMap;
import java.util.Map;

// Length of Longest Subarray With at Most K Frequency
public class N2958 {

    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int l = 0;
        int r = 1;
        int max = 1;
        map.put(nums[l], 1);
        while (r < nums.length) {
            if (map.getOrDefault(nums[r], 0) + 1 > k) {
                while (l < r) {
                    map.put(nums[l], map.get(nums[l]) - 1);
                    if (nums[l] == nums[r]) {
                        l += 1;
                        break;
                    }
                    l += 1;
                }
            }
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            max = Math.max(max, r - l + 1);
            r += 1;
        }

        return max;
    }
}
