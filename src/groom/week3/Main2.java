package groom.week3;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Main2 {

    static char[][] phone = new char[][]{
            {},
            {'1', '.', ',', '?', '!'},
            {'2', 'A', 'B', 'C'},
            {'3', 'D', 'E', 'F'},
            {'4', 'G', 'H', 'I'},
            {'5', 'J', 'K', 'L'},
            {'6', 'M', 'N', 'O'},
            {'7', 'P', 'Q', 'R', 'S'},
            {'8', 'T', 'U', 'V'},
            {'9', 'W', 'X', 'Y', 'Z'}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] input = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int p = input[i];
            if (stack.isEmpty()) {
                stack.push(p);
            } else if (stack.peek() == p) {
                stack.push(p);
            } else {
                sb.append(phone[stack.peek()][(stack.size() - 1) % phone[stack.peek()].length]);
                stack = new Stack<>();
                stack.push(p);
            }
        }

        if (!stack.isEmpty()) {
            sb.append(phone[stack.peek()][(stack.size() - 1)  % phone[stack.peek()].length]);
        }

        System.out.println(sb);
    }
}
