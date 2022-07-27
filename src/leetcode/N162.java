package leetcode;

// https://leetcode.com/problems/find-peak-element/
// Binary Search
public class N162 {
    public static void main(String[] args) {
        new N162().findPeakElement(new int[]{1,2,1,3,5,6,4});
    }

    public int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;

            if (mid == 0) {
                if (nums[mid] > nums[mid + 1]) return mid;
                else left = mid + 1;
                continue;
            } else if (mid == nums.length - 1) {
                if (nums[mid] > nums[mid - 1]) return mid;
                else right = mid - 1;
                continue;
            }

            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {   // peak
                return mid;
            } else if (nums[mid] > nums[mid - 1] && nums[mid] < nums[mid + 1]) {    // ascending
                left = mid + 1;
            } else {    // descending
                right = mid - 1;
            }
        }

        return -1;      // dummy
    }
}
