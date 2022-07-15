package leetcode;

import java.util.*;

// https://leetcode.com/problems/3sum/
// Two Pointer
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new LinkedList<>();

        Arrays.sort(nums);  // 오름차순 정렬

        for (int i = 0; i < nums.length - 2; i++) {         // nums[i]를 고정값으로 설정
            if (i > 0 && nums[i] == nums[i - 1]) continue;  // i 값을 증가시켰는데 nums[i]가 같은 경우는 확인할 필요 X

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];

                if (sum == 0) {
                    answer.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // sum이 0인 경우 left, right를 둘다 바꾸어준다.
                    // nums[left]의 값이 바뀌면 nums[right]의 값도 바꿔야 sum이 0이 될 수 있다.
                    left++;
                    right--;

                    // left를 늘려도 값이 같은 경우 한 번 더 늘린다.
                    while (nums[left] == nums[left - 1] && left < right) {
                        left++;
                    }

                    // rigth를 줄여도 값이 같은 경우 한 번 더 줄인다.
                    while (nums[right] == nums[right + 1] && left < right) {
                        right--;
                    }
                } else if (sum > 0) {
                    right--;    // right 포인터를 줄여 sum을 줄인다.
                } else {
                    left++;     // left 포인터를 줄여 sum을 늘린다.
                }
            }
        }

        return answer;
    }
}
