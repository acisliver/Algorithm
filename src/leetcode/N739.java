package leetcode;

import java.util.Stack;

// Daily Temperatures
public class N739 {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int temp = temperatures[i];
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            if (temperatures[stack.peek()] >= temp) { // 내림차순
                stack.push(i);
            } else {
                while (!stack.isEmpty() && temperatures[stack.peek()] < temp) {
                    int idx = stack.pop();
                    answer[idx] = i - idx;
                }
                stack.push(i);
            }
        }

        return answer;
    }
}
