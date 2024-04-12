package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

// Trapping Rain Water
public class N42 {

    public static void main(String[] args) {
        new N42().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
    }

    public int trap(int[] height) {
        int answer = 0;
        // 증가하는 스택으로 유지
        Deque<Integer> deque = new ArrayDeque<>();

        for (int r = 0; r < height.length; r++) {
            while (!deque.isEmpty() && height[deque.peek()] < height[r]) {
                int m = deque.pop();

                if (deque.isEmpty()) {
                    break;
                }

                int l = deque.peek();
                int h = Math.min(height[r] - height[m], height[l] - height[m]);
                int w = r - l - 1;

                answer += h * w;
            }
            deque.push(r);
        }

        return answer;
    }
}
