package leetcode;

// https://leetcode.com/problems/maximum-subarray/
// 분할 정복
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        return dq(nums, 0, nums.length - 1);
    }

    public int dq(int nums[], int start, int end) {

        if (start == end) {
            return nums[start];
        }

        int mid = (start + end) / 2;
        int sum = 0;
        int leftMaxSUM = Integer.MIN_VALUE;

        for (int i = mid; i >= start; i--) {
            sum += nums[i];
            if (sum > leftMaxSUM) {
                leftMaxSUM = sum;
            }
        }

        sum = 0;    // reset sum to 0
        int rightMaxSUM = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= end; i++) {
            sum += nums[i];
            if (sum > rightMaxSUM) {
                rightMaxSUM = sum;
            }
        }

        int maxLeftRight = Math.max(dq(nums, start, mid), dq(nums, mid + 1, end));

        // 왼쪽 혹은 오른쪽의 최대값과 중간에 걸친 최대값 비교
        return Math.max(maxLeftRight, leftMaxSUM + rightMaxSUM);
    }
}
