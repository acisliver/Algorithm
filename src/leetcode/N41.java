package leetcode;

import java.util.Arrays;

// First Missing Positive
public class N41 {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int num : nums) {
            int abs = Math.abs(num);
            if (abs > 0 && abs <= n) {
                nums[abs - 1] = -Math.abs(nums[abs - 1]);
            }
        }
        System.out.println(Arrays.toString(nums));
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > 0) {
                return i + 1;
            }
            if (num < 0) {
                count += 1;
            }
        }
        return count + 1;
    }
}
