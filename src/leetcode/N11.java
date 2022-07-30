package leetcode;

// https://leetcode.com/problems/container-with-most-water/
// Tow Pointers
public class N11 {

    public static void main(String[] args) {
        System.out.println(
                new N11().maxArea(new int[]{1,2,4,3})
        );
    }

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int area = 0;

        while(left < right) {
            int w = right - left;
            int h = Math.min(height[left], height[right]);

            if (height[left] < height[right]) { // 길이가 긴 height는 더 높게 쓰일 수 있기 때문에 남긴다
                left++;
            } else {
                right--;
            }

            area = Math.max(area, w * h);
        }

        return area;
    }
}
