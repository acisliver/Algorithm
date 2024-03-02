package leetcode;

import java.util.Arrays;

// Squares of a Sorted Array
public class N977 {
    public int[] sortedSquares(int[] nums) {
        int i = nums.length - 1;
        int l = 0;
        int r = nums.length - 1;
        int[] answer = new int[nums.length];

        while (l <= r) {
            int lVal = nums[l] * nums[l];
            int rVal = nums[r] * nums[r];

            if (lVal < rVal) {
                answer[i--] = rVal;
                r -= 1;
            } else {
                answer[i--] = lVal;
                l += 1;
            }
        }

        return answer;
    }
}
