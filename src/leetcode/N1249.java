package leetcode;


import java.util.ArrayDeque;
import java.util.Deque;

// Minimum Remove to Make Valid Parentheses
public class N1249 {

    public static void main(String[] args) {
        new N1249().minRemoveToMakeValid("))((");
    }

    public String minRemoveToMakeValid(String s) {
        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '(' && c != ')') {
                sb.append(c);
                continue;
            }
            if (c == '(') {
                deque.add(i);
                sb.append(c);
            } else {
                if (!deque.isEmpty()) {
                    sb.append(c);
                    deque.pop();
                } else {
                    sb.append(" ");
                }
            }
        }

        while (!deque.isEmpty()) {
            int idx = deque.pop();
            sb.replace(idx, idx + 1, " ");
        }

        return sb.toString().replace(" ", "");
    }
}
