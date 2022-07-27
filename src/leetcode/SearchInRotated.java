package leetcode;

// https://leetcode.com/problems/search-in-rotated-sorted-array/
// Binary Search
public class SearchInRotated {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int midVal = nums[mid];

            if (midVal == target) return mid;

            if (nums[left] <= midVal) { // mid까지 오름차순일 경우
                if (midVal > target && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {                    // mid까지 가는 중간에 pivot이 있어 rotate된 경우
                if (midVal < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
