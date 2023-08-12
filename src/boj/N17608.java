package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import static java.lang.System.in;

// 막대기
// https://www.acmicpc.net/problem/17608
public class N17608 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int bar = Integer.parseInt(br.readLine());
            if (stack.isEmpty()) {
                stack.push(bar);
                continue;
            }
            while (!stack.isEmpty() && stack.peek() <= bar) {
                stack.pop();
            }
            stack.push(bar);
        }

        System.out.println(stack.size());
    }
}
