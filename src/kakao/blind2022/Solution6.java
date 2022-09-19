package kakao.blind2022;

// https://school.programmers.co.kr/learn/courses/30/lessons/92344
// Cumulative Sum
public class Solution6 {
    public static void main(String[] args) {
        Solution6 s = new Solution6();
        System.out.println(
                s.solution(
                        new int[][]{
                                {5, 5, 5, 5, 5},
                                {5, 5, 5, 5, 5},
                                {5, 5, 5, 5, 5},
                                {5, 5, 5, 5, 5}
                        },
                        new int[][]{
                                {1, 0, 0, 3, 4, 4},
                                {1, 2, 0, 2, 3, 2},
                                {2, 1, 0, 3, 1, 2},
                                {1, 0, 1, 3, 3, 1}
                        })
        );
    }

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int[][] skilled = new int[board.length + 1][board[0].length + 1];

        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5] * (type == 1 ? -1 : 1);

            skilled[r1][c1] += degree;
            skilled[r1][c2 + 1] -= degree;
            skilled[r2 + 1][c1] -= degree;
            skilled[r2 + 1][c2 + 1] += degree;
        }

        // 누적합
        // 1. 왼쪽에서 오른쪽
        for (int[] ints : skilled) {
            for (int i = 1; i < ints.length; i++) {
                ints[i] += ints[i - 1];
            }
        }

        // 2. 위쪽에서 아래쪽
        for (int i = 0; i < skilled[0].length; i++) {
            for (int j = 1; j < skilled.length; j++) {
                skilled[j][i] += skilled[j - 1][i];
            }
        }

        // 스킬 결과 적용
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + skilled[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}
