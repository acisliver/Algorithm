package leetcode;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// Binary Search
public class N153 {
    public static void main(String[] args) {
        System.out.println(new N153().findMin(new int[] {3,4,5,1,2}));
    }

    // right를 기준으로 내가 mid가 어느 부분인지 판별 후 최소값 위치까지 탐색
    // 최댓값을 구하는 문제는 left를 기준으로 둘 것 같음
    // 1. right < mid 큰 부분
    // 2. right > mid 작은 부분
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while(left < right) {   // left와 right가 같아진 경우 최소
            int mid = (left + right) / 2;

            if (nums[right] < nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }
}
