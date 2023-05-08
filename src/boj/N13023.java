package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// PPAP
// https://www.acmicpc.net/problem/13023
public class N13023 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char c : str) {
            if (stack.size() < 3) {
                stack.push(c);
                continue;
            }
            char p3 = c;
            char a = stack.pop();
            char p2 = stack.pop();
            char p1  = stack.pop();
            if (p1 == 'P' && p2 == 'P' && p3 == 'P' && a == 'A') {
                stack.push('P');
            } else {
                stack.push(p1);
                stack.push(p2);
                stack.push(a);
                stack.push(p3);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        String result = sb.reverse().toString();

        if (result.equals("P") || result.equals("PPAP")) {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }

}
