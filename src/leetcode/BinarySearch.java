package leetcode;

public class BinarySearch {
    public int search(int[] nums, int target) {

        int from = 0;
        int to = nums.length - 1;

        while (from <= to) {
            int mid = (from + to) >>> 1;
            int midVal = nums[mid];

            if (midVal == target) return mid;
            else if (midVal < target) from = mid + 1;
            else to = mid - 1;
        }

        return -1;
    }
}
