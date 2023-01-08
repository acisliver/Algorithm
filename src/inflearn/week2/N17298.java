package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

// 오큰수
// https://www.acmicpc.net/problem/17298
public class N17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] sequence = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < N; i++) {

            while(!stack.isEmpty() && sequence[stack.peek()] < sequence[i]) {
                sequence[stack.pop()] = sequence[i];
            }

            stack.push(i);
        }

        while(!stack.isEmpty()) {
            sequence[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(sequence[i]).append(' ');
        }

        System.out.println(sb);
    }
}
