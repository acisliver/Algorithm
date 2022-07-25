package leetcode;


// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
// Binary Search
public class SearchRange {
    public static void main(String[] args) {
        System.out.println(new SearchRange().searchRange(new int[] {5,7,7,8,8,10}, 8));
    }

    public int[] searchRange(int[] nums, int target) {
        int[] answer = new int[] {
                findFirst(nums, target),
                findLast(nums, target)
        };
        return answer;
    }

    // 하한 탐색
    public int findFirst(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int midVal = nums[mid];

            if (midVal < target) {          // 범위의 오른쪽에 타겟 존재
                left = mid + 1;
            } else if (midVal > target) {   // 범위의 왼쪽에 타겟 존재
                right = mid - 1;
            } else {                        // 타겟과 midVal이 일치
                index = mid;                // 하한 갱신

                // 갱신한 하한보다 작은 하한으로 범위 축소
                right = mid - 1;
            }
        }

        return index;
    }

    // 상한 탐색
    public int findLast(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int midVal = nums[mid];

            if (midVal > target) {          // 범위 왼쪽에 타겟 존재
                right = mid - 1;
            } else if (midVal < target) {   // 범위 오른쪽에 타겟 존재
                left = mid + 1;
            } else {                        // 타겟과 midVal이 일치
                index = mid;    // 상한 갱신

                // 갱신한 상한보다 큰 상한으로 범위 축소
                left = mid + 1;
            }
        }

        return index;
    }
}
