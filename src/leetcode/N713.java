package leetcode;

// https://leetcode.com/problems/subarray-product-less-than-k/
// Sliding Window
public class N713 {

    // 풀이
    // k값을 윈도우로 두고 k값보다 큰 값이 나타나면
    // 값을 줄이려고 왼쪽 포인터를 감소시킨다.
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;

        int answer = 0;
        int left = 0;
        int right = 0;
        int product = 1;

        while (right < nums.length) {
            product *= nums[right]; // 우선 product를 곱하며 subarray의 범위를 늘린다.

            while (left <= right && product >= k) {  // k보다 큰 값 나타남 -> 윈도우를 넘어섬
                product /= nums[left++];
            }

            answer += right - left + 1; // 이 부분에 대해선 블로그에 정리하겠다.
            right++;
        }

        return answer;
    }
}
