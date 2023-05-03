package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 구슬 탈출 2
// https://www.acmicpc.net/problem/13460
public class N13460 {

    // up, down, left, right
    final static int[] DR = {-1, 1, 0, 0};
    final static int[] DC = {0, 0, -1, 1};
    static int N, M;
    static char[][] BOARD;
    static int[] GOAL, RED, BLUE;
    static int ANSWER = 11;
    static boolean RED_IS_FIRST;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        BOARD = new char[N][M];
        for (int i = 0; i < N; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                BOARD[i][j] = charArray[j];
                if (BOARD[i][j] == 'O') {
                    GOAL = new int[]{i, j};
                } else if (BOARD[i][j] == 'R') {
                    BOARD[i][j] = '.';
                    RED = new int[]{i, j};
                } else if (BOARD[i][j] == 'B') {
                    BOARD[i][j] = '.';
                    BLUE = new int[]{i, j};
                }
            }
        }

        lean(0, 0, RED, BLUE);  // up
        lean(1, 0, RED, BLUE);  // down
        lean(2, 0, RED, BLUE);  // left
        lean(3, 0, RED, BLUE);  // right

        System.out.println(ANSWER > 10 ? -1 : ANSWER);
    }

    private static void lean(int d, int count, int[] red, int[] blue) {
        if (count > 10) {
            return;
        }

        if (isSamePath(red, blue, d)) {
            int[][] result = moveConcurrency(red, blue, d);
            red = result[0];
            blue = result[1];
        } else {
            red = move(red, d);
            blue = move(blue, d);
        }

        if (isGoal(blue)) {
            return;
        }

        if (isGoal(red)) {
            ANSWER = Math.min(count + 1, ANSWER);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (d == i) continue;
            lean(i, count + 1, red, blue);
        }
    }

    private static int[][] moveConcurrency(int[] red, int[] blue, int d) {
        RED_IS_FIRST = true;
        int[][] firstAndSecond = getFirstAndSecond(red, blue, d);
        int[] first = firstAndSecond[0];
        int[] second = firstAndSecond[1];
        int[][] result = moveInOrder(first, second, d);
        return RED_IS_FIRST ? result : new int[][]{result[1], result[0]};
    }

    private static int[][] getFirstAndSecond(int[] red, int[] blue, int d) {
        int[] first;
        int[] second;

        switch (d) {
            case 0: // up
                if (red[0] < blue[0]) {
                    first = red;
                    second = blue;
                } else {
                    first = blue;
                    second = red;
                    RED_IS_FIRST = false;
                }
                break;
            case 1: // down
                if (red[0] > blue[0]) {
                    first = red;
                    second = blue;
                } else {
                    first = blue;
                    second = red;
                    RED_IS_FIRST = false;
                }
                break;
            case 2: // left
                if (red[1] < blue[1]) {
                    first = red;
                    second = blue;
                } else {
                    first = blue;
                    second = red;
                    RED_IS_FIRST = false;
                }
                break;
            case 3: // right
                if (red[1] > blue[1]) {
                    first = red;
                    second = blue;
                } else {
                    first = blue;
                    second = red;
                    RED_IS_FIRST = false;
                }
                break;
            default:
                throw new IllegalArgumentException();
        }

        return new int[][]{first, second};
    }

    private static int[][] moveInOrder(int[] first, int[] second, int d) {
        int[] firstPos = move(first, d);
        int[] secondPos = move(second, d);

        if (isGoal(firstPos)) {
            return new int[][]{firstPos, secondPos};
        }

        if (firstPos[0] == secondPos[0] && firstPos[1] == secondPos[1]) {
            secondPos[0] -= DR[d];
            secondPos[1] -= DC[d];
        }

        return new int[][]{firstPos, secondPos};
    }

    private static int[] move(int[] pos, int d) {
        int curR = pos[0];
        int curC = pos[1];
        while (true) {
            int nextR = curR + DR[d];
            int nextC = curC + DC[d];

            if (BOARD[nextR][nextC] == '#') break;

            curR = nextR;
            curC = nextC;

            if (BOARD[nextR][nextC] == 'O') break;
        }

        return new int[]{curR, curC};
    }

    private static boolean isGoal(int[] pos) {
        return pos[0] == GOAL[0] && pos[1] == GOAL[1];
    }

    private static boolean isSamePath(int[] red, int[] blue, int d) {

        if (d == 0 || d == 1) { // up, down
            return isSameCol(red, blue);
        }
        if (d == 2 || d == 3) {  // left, right
            return isSameRow(red, blue);
        }

        throw new IllegalArgumentException(String.valueOf(d));
    }

    private static boolean isSameRow(int[] red, int[] blue) {
        if (red[0] != blue[0]) {
            return false;
        }

        int start = Math.min(red[1], blue[1]) + 1;
        int end = Math.max(red[1], blue[1]);
        for (int i = start; i < end; i++) {
            char c = BOARD[red[0]][i];
            if (c == '#') {
                return false;
            }
        }

        return true;
    }

    private static boolean isSameCol(int[] red, int[] blue) {
        if (red[1] != blue[1]) {
            return false;
        }

        int start = Math.min(red[0], blue[0]) + 1;
        int end = Math.max(red[0], blue[0]);
        for (int i = start; i < end; i++) {
            char c = BOARD[i][red[1]];
            if (c == '#') {
                return false;
            }
        }

        return true;
    }

}
