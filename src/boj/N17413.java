package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.in;

// 단어 뒤집기 2
// https://www.acmicpc.net/problem/17413
public class N17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        char[] chars = br.readLine().toCharArray();
        List<Character> answer = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        boolean isTag = false;

        for (char c : chars) {
            if (c == '<') {
                while (!stack.isEmpty()) {
                    answer.add(stack.pop());
                }
                isTag = true;
                answer.add(c);
                continue;
            }

            if (isTag) {
                answer.add(c);
                if (c == '>') {
                    isTag = false;
                }
                continue;
            }

            if (c != ' ') {
                stack.push(c);
            } else {
                while (!stack.isEmpty()) {
                    answer.add(stack.pop());
                }
                answer.add(' ');
            }
        }

        while (!stack.isEmpty()) {
            answer.add(stack.pop());
        }

        System.out.println(answer.stream().map(String::valueOf).collect(Collectors.joining()));
    }
}
