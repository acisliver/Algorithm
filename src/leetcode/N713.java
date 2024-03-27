package leetcode;

// Subarray Product Less Than K
public class N713 {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;

        int answer = 0;
        int left = 0;
        int right = 0;
        int product = 1;

        while (right < nums.length) {
            product *= nums[right];

            while (left <= right && product >= k) {
                product /= nums[left++];
            }

            answer += right - left + 1;
            right++;
        }

        return answer;
    }
}
