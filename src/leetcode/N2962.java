package leetcode;

import java.util.ArrayList;
import java.util.List;

// Count Subarrays Where Max Element Appears at Least K Times
public class N2962 {
    public long countSubarrays(int[] nums, int k) {
        long answer = 0;
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }

        List<Integer> maxIdx = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == max) {
                maxIdx.add(i);
            }
        }

        if (maxIdx.size() < k) {
            return 0;
        }

        int prevIdx = -1;
        for (int i = 0; i < maxIdx.size() - k + 1; i++) {
            int lIdx = maxIdx.get(i);
            int rIdx = maxIdx.get(i + k - 1);
            System.out.printf("%d, %d\n", lIdx, rIdx);
            answer += (long) (nums.length - rIdx) * (lIdx - prevIdx);
            prevIdx = lIdx;
        }

        return answer;
    }

    public long countSubarrays2(int[] nums, int k) {
        long answer = 0;
        int max = 0;
        int l = 0;
        int r = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }

        while(r < nums.length) {
            k -= nums[r] == max ? 1 : 0;
            r += 1;
            while (k == 0) {
                k += nums[l] == max ? 1 : 0;
                l += 1;
            }
            answer += l;
        }

        return answer;
    }
}
