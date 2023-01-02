package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 유기농 배추
// https://www.acmicpc.net/problem/1012
public class N1012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCases; i++) {
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[1]);
            int m = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[2]);
            boolean[][] field = new boolean[n][m];
            for (int j = 0; j < k; j++) {
                int[] location = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                field[location[1]][location[0]] = true;
            }
            int count = calculateWarmCount(field);
            System.out.println(count);
        }
    }

    private static int calculateWarmCount(boolean[][] field) {
        int warmCount = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j]) {
                    warmCount += 1;
                    eraseCabbage(i, j, field);
                }
            }
        }

        return warmCount;
    }

    private static void eraseCabbage(int row, int col, boolean[][] field) {

        int[] dRow = {0, 0, 1, -1};
        int[] dCol = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        field[row][col] = false;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = current[0] + dRow[i];
                int nextCol = current[1] + dCol[i];

                if (nextRow < 0 || nextCol < 0 || nextRow >= field.length || nextCol >= field[0].length) {
                    continue;
                }

                if (field[nextRow][nextCol]) {
                    field[nextRow][nextCol] = false;
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
        }
    }

}
