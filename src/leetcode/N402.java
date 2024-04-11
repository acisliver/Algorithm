package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class N402 {
    public String removeKdigits(String num, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (char c : num.toCharArray()) {
            int n = Character.getNumericValue(c);
            while (!deque.isEmpty() && k > 0 && deque.peek() > n) {
                deque.pop();
                k -= 1;
            }
            deque.push(n);
        }

        while (k > 0 && !deque.isEmpty()) {
            deque.pop();
            k -= 1;
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.insert(0, deque.pop());
        }

        while (!sb.isEmpty() && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return !sb.isEmpty() ? sb.toString() : "0";
    }
}
