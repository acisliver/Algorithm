package programers;

import java.util.LinkedList;
import java.util.Queue;

// 리코쳇 로봇
// https://school.programmers.co.kr/learn/courses/30/lessons/169199
public class N169199 {

    private static final int[] DR = {0, 0, 1, -1};
    private static final int[] DC = {1, -1, 0, 0};

    public static void main(String[] args) {
        N169199 n = new N169199();
        System.out.println(n.solution(new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."}));
    }

    public int solution(String[] board) {
        int move = 0;

        boolean[][] visited = new boolean[board.length][board[0].length()];
        int[] robotPos = getRobotPos(board);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(robotPos);
        visited[robotPos[0]][robotPos[1]] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();

            System.out.printf("move: %d\n", move);
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int cR = cur[0];
                int cC = cur[1];

                if (board[cR].charAt(cC) == 'G') return move;

                for (int j = 0; j < 4; j++) {
                    int nR = cR + DR[j];
                    int nC = cC + DC[j];

                    // 한 방향으로 쭉 이동
                    while (true) {
                        if (nR < 0 || nC < 0 || nR >= board.length || nC >= board[0].length() || board[nR].charAt(nC) == 'D') {
                            if (visited[nR - DR[j]][nC - DC[j]]) break;
                            visited[nR - DR[j]][nC - DC[j]] = true;
                            queue.offer(new int[]{nR - DR[j], nC - DC[j]});
                            System.out.printf("%d %d\n", nR - DR[j], nC - DC[j]);
                            break;
                        }

                        nR += DR[j];
                        nC += DC[j];
                    }
                }
            }

            move += 1;
        }

        return -1;
    }


    int[] getRobotPos(String[] board) {
        int[] pos = {};

        for (int i = 0; i < board.length; i++) {
            String row = board[i];
            for (int j = 0; j < row.length(); j++) {
                char cur = row.charAt(j);
                if (cur == 'R') {
                    return new int[]{i, j};
                }
            }
        }

        return pos;
    }
}
