package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.in;

// 스도쿠
// https://www.acmicpc.net/problem/2580
public class N2580 {

    static List<int[]> BLANKS = new ArrayList<>();
    static boolean FOUND = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int[][] board = new int[9][9];

        for (int i = 0; i < board.length; i++) {
            board[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    BLANKS.add(new int[]{i, j});
                }
            }
        }

        solve(0, board);
    }

    private static void solve(int index, int[][] board) {
        if (index == BLANKS.size()) {
            System.out.println(answerToString(board));
            FOUND = true;
            return;
        }

        int[] pos = BLANKS.get(index);
        int r = pos[0];
        int c = pos[1];
        for (int i = 1; i < 10; i++) {
            if (FOUND) return;
            board[r][c] = i;
            if (isInvalid(index, board)) {
                continue;
            }
            solve(index + 1, board);
        }
        board[r][c] = 0;
    }

    private static boolean isInvalid(int index, int[][] board) {

        if (isInvalidRow(index, board)) {
            return true;
        }

        if (isInvalidCol(index, board)) {
            return true;
        }

        if (isInvalidSquare(index, board)) {
            return true;
        }

        return false;
    }

    private static boolean isInvalidRow(int index, int[][] board) {
        int[] pos = BLANKS.get(index);
        int r = pos[0];
        int c = pos[1];
        int targetNumber = board[r][c];

        for (int i = 0; i < board.length; i++) {
            if (i == r) {
                continue;
            }
            int number = board[i][c];
            if (number == targetNumber) {
                return true;
            }
        }

        return false;
    }

    private static boolean isInvalidCol(int index, int[][] board) {
        int[] pos = BLANKS.get(index);
        int r = pos[0];
        int c = pos[1];
        int targetNumber = board[r][c];

        for (int i = 0; i < board.length; i++) {
            if (i == c) {
                continue;
            }
            int number = board[r][i];
            if (number == targetNumber) {
                return true;
            }
        }

        return false;
    }

    private static boolean isInvalidSquare(int index, int[][] board) {
        final int[] dR = {0, 0, 0, 1, 1, 1, 2, 2, 2};
        final int[] dC = {0, 1, 2, 0, 1, 2, 0, 1, 2};

        int[] pos = BLANKS.get(index);
        int r = pos[0];
        int c = pos[1];
        int sR = r / 3 * 3;
        int sC = c / 3 * 3;
        int targetNumber = board[r][c];

        for (int i = 0; i < dR.length; i++) {
            int nextR = sR + dR[i];
            int nextC = sC + dC[i];
            int number = board[nextR][nextC];
            if (r == nextR && c == nextC) {
                continue;
            }
            if (number == targetNumber) {
                return true;
            }
        }

        return false;
    }

    private static String answerToString(int[][] answer) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : answer) {
            String rowString = Arrays.stream(row)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
            sb.append(rowString).append("\n");
        }

        return sb.toString();
    }

}
