package inflearn.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 좋은 단어
// https://www.acmicpc.net/problem/3986
public class N3986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int answer = 0;
        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            Stack<Character> stack = new Stack<>();
            for (char aChar : chars) {
                if (stack.isEmpty()) {
                    stack.push(aChar);
                } else {
                    if (stack.peek() == aChar) {
                        stack.pop();
                    } else {
                        stack.push(aChar);
                    }
                }
            }
            if (stack.size() == 0) {
                answer += 1;
            }
        }

        System.out.println(answer);
    }
}
