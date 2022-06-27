package leetcode;

// https://leetcode.com/problems/merge-sorted-array/
// 합병정렬, 분할정복
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int end = m + n - 1;

        // 뒤에서부터 큰 숫자를 삽입
        while (p1>= 0 && p2 >= 0) {
            nums1[end--] = nums1[p1] > nums2[p2] ?
                    nums1[p1--] : nums2[p2--];
        }

        // num2가 남는 경우밖에 없다.
        // num1이 남으면 그 경우가 정렬된 상태이다.
        while(p2 >= 0) {
            nums1[end--] = nums2[p2--];
        }
    }
}
