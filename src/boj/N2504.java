package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import static java.lang.System.in;

// 괄호의 값
// https://www.acmicpc.net/problem/2504
public class N2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        char[] str = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        int answer = 0;
        int temp = 1;
        boolean flag = false;

        for (char cur : str) {
            if (cur == '(' || cur == '[') {
                stack.push(cur);
                temp *= cur == '(' ? 2 : 3;
                flag = false;
                continue;
            }

            if (stack.isEmpty()) {
                System.out.println(0);
                return;
            }

            char prev = stack.pop();
            if (prev == '(') {
                if (cur != ')') {
                    System.out.println(0);
                    return;
                }
                if (flag) {
                    temp /= 2;
                    continue;
                }
                answer += temp;
                temp /= 2;
                flag = true;
            }
            if (prev == '[') {
                if (cur != ']') {
                    System.out.println(0);
                    return;
                }
                if (flag) {
                    temp /= 3;
                    continue;
                }
                answer += temp;
                temp /= 3;
                flag = true;
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(0);
            return;
        }

        System.out.println(answer);
    }

}
