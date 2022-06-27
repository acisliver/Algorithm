package leetcode;

// https://leetcode.com/problems/two-sum/
// 브루트 포스 - 이중포문
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] answer = new int[2];

        int sum;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                sum = nums[i] + nums[j];
                if (sum == target) answer = new int[] {i, j};
            }
        }

        return answer;
    }
}
