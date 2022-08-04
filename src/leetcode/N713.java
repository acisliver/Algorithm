package leetcode;

// https://leetcode.com/problems/subarray-product-less-than-k/
// Sliding Window
public class N713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;

        int answer = 0;

        for (int start = 0; start < nums.length; start++) {
            double temp = 1;
            for (int next = start; next < nums.length; next++) {
                temp *= nums[next];

                if (temp < k) {
                    answer++;
                } else {
                    break;
                }
            }
        }

        return answer;
    }
}
