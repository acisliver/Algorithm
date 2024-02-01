package leetcode;

import java.util.Arrays;

// Divide Array Into Arrays With Max Difference
public class N2966 {
    public int[][] divideArray(int[] nums, int k) {
        if (nums.length % 3 != 0) {
            return new int[0][0];
        }
        int[][] answer = new int[nums.length / 3][3];
        int idx = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i += 3) {
            int a = nums[i];
            int b = nums[i + 1];
            int c = nums[i + 2];
            if (c - a > k) {
                return new int[0][0];
            }
            answer[idx][0] = a;
            answer[idx][1] = b;
            answer[idx][2] = c;
            idx += 1;
        }

        return answer;
    }
}
