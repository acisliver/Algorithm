package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 불!
// https://www.acmicpc.net/problem/4179
public class N4179 {

    static int N, M;
    static char[][] MIRO;
    static int[][] DISTANCE;
    static int[] dRow = {1, -1, 0, 0};
    static int[] dCol = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        MIRO = new char[N][M];
        DISTANCE = new int[N][M];
        Queue<int[]> jQueue = new LinkedList<>();
        Queue<int[]> fQueue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            MIRO[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (MIRO[i][j] == 'J') {
                    jQueue.offer(new int[]{i, j});
                    DISTANCE[i][j] = 1;
                } else if (MIRO[i][j] == 'F') {
                    fQueue.offer(new int[]{i, j});
                }
            }
        }

        while (!jQueue.isEmpty()) {

            int size = fQueue.size();
            for (int i = 0; i < size; i++) {
                int[] curFire = fQueue.poll();
                int curRow = curFire[0];
                int curCol = curFire[1];

                for (int j = 0; j < 4; j++) {
                    int nextRow = curRow + dRow[j];
                    int nextCol = curCol + dCol[j];

                    if (isOut(nextRow, nextCol)) continue;
                    if (MIRO[nextRow][nextCol] == '#' || MIRO[nextRow][nextCol] == 'F') continue;

                    MIRO[nextRow][nextCol] = 'F';
                    fQueue.offer(new int[]{nextRow, nextCol});
                }
            }

            size = jQueue.size();
            for (int i = 0; i < size; i++) {
                int[] curJihun = jQueue.poll();
                int curRow = curJihun[0];
                int curCol = curJihun[1];

                if (curRow == 0 || curCol == 0 || curRow == N - 1 || curCol == M - 1) {
                    System.out.println(DISTANCE[curRow][curCol]);
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int nextRow = curRow + dRow[j];
                    int nextCol = curCol + dCol[j];

                    if (isOut(nextRow, nextCol)) continue;
                    if (MIRO[nextRow][nextCol] == '#' || MIRO[nextRow][nextCol] == 'F') continue;
                    if (DISTANCE[nextRow][nextCol] != 0) continue;

                    DISTANCE[nextRow][nextCol] = DISTANCE[curRow][curCol] + 1;
                    jQueue.offer(new int[]{nextRow, nextCol});
                }
            }
        }

        System.out.println("IMPOSSIBLE");

    }

    private static boolean isOut(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= M;
    }

}
