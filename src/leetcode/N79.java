package leetcode;

// Word Search
public class N79 {

    public static void main(String[] args) {
        new N79().exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "SEE");
    }

    private static boolean answer;
    private static boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        answer = false;
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    dfs(i, j, 1, board, word);
                    if (answer) {
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    private void dfs(int r, int c, int idx, char[][] board, String word) {
        if (answer) {
            return;
        }

        if (idx == word.length()) {
            answer = true;
            return;
        }

        final int[] dR = {0, 0, 1, -1};
        final int[] dC = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int nR = r + dR[i];
            int nC = c + dC[i];

            if (nR < 0 || nC < 0 || nR >= board.length || nC >= board[0].length) {
                continue;
            }

            if (visited[nR][nC]) {
                continue;
            }

            if (board[nR][nC] == word.charAt(idx)) {
                System.out.print(board[nR][nC]);
                visited[nR][nC] = true;
                dfs(nR, nC, idx + 1, board, word);
                visited[nR][nC] = false;
            }
        }
    }
}
