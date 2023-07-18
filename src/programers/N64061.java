package programers;

import java.util.Stack;

// 크레인 인형뽑기 게임
// https://school.programmers.co.kr/learn/courses/30/lessons/64061
public class N64061 {
    public static void main(String[] args) {
        N64061 s = new N64061();
        System.out.println(s.solution(
                new int[][]{
                        {0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 3},
                        {0, 2, 5, 0, 1},
                        {4, 2, 4, 4, 2},
                        {3, 5, 1, 3, 1}
                },
                new int[] {1, 5, 3, 5, 1, 2, 1, 4}
                ));
    }

    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int move : moves) {
            for (int[] row : board) {
                int doll = row[move - 1];
                if (doll == 0) {
                    continue;
                }

                row[move - 1] = 0;

                if (stack.isEmpty()) {
                    stack.push(doll);
                    break;
                }

                int peek = stack.peek();
                if (peek == doll) {
                    stack.pop();
                    answer += 2;
                    break;
                }

                stack.push(doll);
                break;
            }
        }

        return answer;
    }
}
