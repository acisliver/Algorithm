package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

// 가스관
// https://www.acmicpc.net/problem/2931
public class N2931 {

    static int N, K;
    static char[][] BOARD;
    static int[] M, Z, PIPE_POSE;
    static char PIPE_TYPE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        K = input[1];
        for (int i = 0; i < N; i++) {
            BOARD[i] = br.readLine().toCharArray();
            for (int j = 0; j < K; j++) {
                if (BOARD[i][j] == 'M') {
                    M = new int[]{i, j};
                }
                if (BOARD[i][j] == 'Z') {
                    Z = new int[]{i, j};
                }
            }
        }

        boolean[][] visited = new boolean[N][K];
        visited[M[0]][M[1]] = true;
        backtracking(M[0], M[1], visited, false);
    }

    private static void backtracking(int i, int j, boolean[][] visited, boolean isAddPipe) {
        final char[] pipes = {'|', '-', '+', '1', '2', '3', '4'};

        if (i == Z[0] && j == Z[1]) {
            System.out.printf("%d %d %s", PIPE_POSE[0] + 1, PIPE_POSE[1] + 1, PIPE_TYPE);
            return;
        }

        if (i == M[0] && j == M[1]) {
            // up, down, left, right
        }

        // up
        if (moveUp(i, j, visited)) {
            backtracking(i - 1, j, visited, isAddPipe);
        }

        // down

        // left

        // right
    }

    private static boolean isOutOfBounds(int i, int j) {
        return i < 0 || j < 0 || i >= N || j >= K;
    }

    private static boolean checkCurPipe(int i, int j, int d) {
        final int[] dI = {-1, 1, 0, 0};
        final int[] dJ = {0, 0, -1, 1};

        return false;
    }

    private static boolean moveUp(int i, int j, boolean[][] visited) {
        int nI = i - 1;
        int nJ = j;
        if (isOutOfBounds(nI, nJ)) return false;
        if (!checkCurPipe(i, j, 0)) return false;
        if (visited[nI][nJ]) return false;

        char nextPipe = BOARD[nI][nJ];
        if (nextPipe == '.') return false;
        if (nextPipe == '-') return false;
        if (nextPipe == '2') return false;
        if (nextPipe == '3') return false;

        return true;
    }

    private static boolean moveDown(int i, int j, boolean[][] visited) {
        return false;
    }

    private static boolean moveLeft(int i, int j, boolean[][] visited) {
        return false;
    }

    private static boolean moveRight(int i, int j, boolean[][] visited) {
        return false;
    }
}
