package leetcode;

// Count Subarrays With Fixed Bounds
public class N2444 {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long answer = 0;
        int l = 0;
        int r = 0;
        int k = -1;
        while (r < nums.length) {
            if (nums[r] < minK || nums[r] > maxK) {
                answer += solution(nums, l, r - 1, minK, maxK);
                l = r + 1;
                k = r;
            }
            r += 1;
        }
        answer += solution(nums, k + 1, r - 1, minK, maxK);

        return answer;
    }

    private long solution(int[] nums, int s, int e, int minK, int maxK) {
        long answer = 0;
        int min = 0;
        int max = 0;
        boolean isMin = false;
        boolean isMax = false;
        for (int i = s; i <= e; i++) {
            if (nums[i] == minK) {
                min = i;
                isMin = true;
            }
            if (nums[i] == maxK) {
                max = i;
                isMax = true;
            }
            if (isMin && isMax) {
                answer += Math.min(min, max) - s + 1;
            }
        }

        return answer;
    }
}
