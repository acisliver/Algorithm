package inflearn.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 현욱은 괄호왕이야!
// https://www.acmicpc.net/problem/15926
public class N15926 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] str = br.readLine().toCharArray();
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        stack.push(-1);

        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    answer = Math.max(answer, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }

        System.out.println(answer);
    }
}
