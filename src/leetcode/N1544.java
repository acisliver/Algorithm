package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

// Make The String Great
public class N1544 {
    public String makeGood(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && Math.abs(c - stack.peek()) == 32) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        return sb.toString();
    }
}
