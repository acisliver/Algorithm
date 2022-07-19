package leetcode;

// https://leetcode.com/problems/search-insert-position/submissions/
// Binary Search
public class SearchInsert {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = (left + right) >>> 1;
            int midVal = nums[mid];

            if (midVal == target) return mid;
            else if (midVal > target) right = mid - 1;
            else left = mid + 1;
        }

        return left;
    }
}
