package inflearn.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 오아시스 재결합
// https://www.acmicpc.net/problem/3015
/*
    서로 보이는 쌍 -> Stack 고려
    1. stack에 키와, 현재 위치에서 볼 수 있는 사람의 수를 저장
    2. 이전 키보다 큰 키가 오면 다음 사람들은 큰 키 이전의 사람을 볼 수 없다.
        2.1 큰 키가 오면 이전 작은 키들을 pop
        2.2 만약 같은 키가 오면 누적

 */
public class N3015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<int[]> stack = new Stack<>();
        long answer = 0;

        for (int i = 0; i < n; i++) {
            int next = Integer.parseInt(br.readLine());
            int sameHeight = 1;  // 나 포함 뒤에 키가 같은 사람 수

            // 큰 키가 면
            while (!stack.isEmpty() && stack.peek()[0] <= next) {
                int[] smaller = stack.pop();
                answer += smaller[1];      // 지금까지 센 볼 수 있는 사람 계산

                if (smaller[0] == next) {  // 다음 사람이 이전 사람과 키가 같을 경우 누적 후 큰 것이 왔을 때 계산
                    sameHeight += smaller[1];
                } else {    // 다음 사람이 이전 사람보다 키가 클 경우
                    sameHeight = 1;
                }
            }

            if (!stack.isEmpty()) { // 작은 사람이 큰 사람을 보는 경우
                answer += 1;
            }
            stack.push(new int[]{next, sameHeight});
        }

        System.out.println(answer);
    }
}
