package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 색종이 붙이기
// https://www.acmicpc.net/problem/17136
public class N17136 {

    static int[] PAPERS = {5, 5, 5, 5, 5};

    static int ANSWER = 30;
    static int[][] BOARD = new int[10][10];
    static boolean[][] VISITED = new boolean[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            BOARD[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        search(0, 0, 0);
        System.out.println(ANSWER == 30 ? -1 : ANSWER);
    }

    private static void search(int r, int c, int count) {

        if (r == 10 && c == 0) {
            ANSWER = Math.min(count, ANSWER);
            return;
        }

        if (BOARD[r][c] == 0 || VISITED[r][c]) {
            int[] next = getNext(1, r, c);
            search(next[0], next[1], count);
            return;
        }

        for (int s = 1; s <= 5; s++) {
            if (is(s, r, c)) {
                fill(s, r, c);
                int[] next = getNext(s, r, c);
                search(next[0], next[1], count + 1);
                unFill(s, r, c);
            }
        }
    }

    static boolean is(int size, int r, int c) {
        if (PAPERS[size - 1] == 0) return false;

        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (i > 9 || j > 9) return false;
                if (VISITED[i][j]) return false;
                if (BOARD[i][j] == 0) return false;
            }
        }
        return true;
    }

    static void fill(int size, int r, int c) {
        PAPERS[size - 1] -= 1;

        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                VISITED[i][j] = true;
            }
        }
    }

    static void unFill(int size, int r, int c) {
        PAPERS[size - 1] += 1;

        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                VISITED[i][j] = false;
            }
        }
    }

    private static int[] getNext(int size, int r, int c) {
        if (c + size >= 10) {
            return new int[]{r + 1, 0};
        }

        return new int[]{r, c + size};
    }

}
