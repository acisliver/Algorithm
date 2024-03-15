package leetcode;

// Product of Array Except Self
// Time Complex: O(n)
// Space Complex: O(n)
public class N238 {
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length + 1];
        int[] right = new int[nums.length + 1];
        int[] answer = new int[nums.length];

        left[0] = 1;
        right[nums.length] = 1;

        for (int i = 1; i < left.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        for (int i = nums.length - 1; i >= 1; i--) {
            right[i] = right[i + 1] * nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            answer[i] = left[i] * right[i + 1];
        }

        return answer;
    }
}
