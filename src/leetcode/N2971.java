package leetcode;

import java.util.Arrays;

// Find Polygon With the Largest Perimeter
public class N2971 {
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        long sum = 0;
        long answer = -1;
        for (int i = 0; i < nums.length; i++) {
            if (sum > nums[i]) {
                answer = sum + nums[i];
            }
            sum += nums[i];
        }

        return answer;
    }
}
