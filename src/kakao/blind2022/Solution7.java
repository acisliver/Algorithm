package kakao.blind2022;

public class Solution7 {
    public static void main(String[] args) {

    }

    static int answer;
    static int[] DI = new int[]{-1, 1, 0, 0};
    static int[] DJ = new int[]{0, 0, -1, 1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        answer = -1;

        moveA(board, aloc, bloc, false, 0);

        return answer;
    }

    private boolean moveA(int[][] board, int[] aloc, int[] bloc, boolean aWin, int move) {

        int row = aloc[0];
        int col = aloc[1];

        if (board[row][col] == 0) {
            if (aWin) answer = Math.min(answer, move);
            return false;
        }

        board[row][col] = 0;
        for (int i = 0; i < 4; i++) {
            int nextI = row - DI[i];
            int nextJ = col - DJ[i];

            if (nextI < 0 || nextJ < 0 || nextI >= board.length || nextJ >= board[0].length) continue;

            if (board[nextI][nextJ] == 0) continue;


            aWin |= moveB(board, new int[]{nextI, nextJ}, bloc, aWin, move + 1);
        }
        board[row][col] = 1;

        return aWin;
    }

    private boolean moveB(int[][] board, int[] aloc, int[] bloc, boolean aWin, int move) {

        int row = bloc[0];
        int col = bloc[1];

        if (board[row][col] == 0) {
            if (!aWin) answer = Math.min(answer, move);
            return false;
        }

        board[row][col] = 0;
        for (int i = 0; i < 4; i++) {
            int nextI = row - DI[i];
            int nextJ = col - DJ[i];

            if (nextI < 0 || nextJ < 0 || nextI >= board.length || nextJ >= board[0].length) continue;

            if (board[nextI][nextJ] == 0) continue;

            aWin |= moveB(board, aloc, new int[]{nextI, nextJ}, aWin, move + 1);
        }
        board[row][col] = 1;

        return aWin;
    }
}
