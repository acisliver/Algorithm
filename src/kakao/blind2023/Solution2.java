package kakao.blind2023;

import java.util.Stack;

// 택배 배달과 수거하기
// https://school.programmers.co.kr/learn/courses/30/lessons/150369
public class Solution2 {

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        System.out.println(s.solution(2, 7, new int[]{8, 0, 2, 0, 1, 0, 2}, new int[]{0, 2, 0, 1, 0, 2, 0}));
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        Stack<Integer> dStack = new Stack<>();
        Stack<Integer> pStack = new Stack<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < deliveries[i]; j++) {
                dStack.push(i + 1);
            }
            for (int j = 0; j < pickups[i]; j++) {
                pStack.push(i + 1);
            }
        }

        while (!dStack.isEmpty() && !pStack.isEmpty()) {
            int lastD = dStack.peek();
            int lastP = pStack.peek();

            for (int i = 0; i < cap; i++) {
                if (!dStack.isEmpty()) dStack.pop();
                if (!pStack.isEmpty()) pStack.pop();
            }

            answer += Math.max(lastD, lastP) * 2L;
        }

        while (!dStack.isEmpty()) {
            int last = dStack.peek();

            for (int i = 0; i < cap; i++) {
                if (!dStack.isEmpty()) dStack.pop();
            }

            answer += last * 2L;
        }

        while (!pStack.isEmpty()) {
            int last = pStack.peek();

            for (int i = 0; i < cap; i++) {
                if (!pStack.isEmpty()) pStack.pop();
            }

            answer += last * 2L;
        }

        return answer;
    }
}
