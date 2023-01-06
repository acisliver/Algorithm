package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 치즈
// https://www.acmicpc.net/problem/2636
public class N2636 {

    static int N, M, LAST_CHEESE;
    static int[][] CHEESE;
    static List<int[]> MELTING_CHEESE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        LAST_CHEESE = 0;
        CHEESE = new int[N][M];

        for (int i = 0; i < N; i++) {
            CHEESE[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int time = getMeltingTime();
        System.out.println(time);
        System.out.println(LAST_CHEESE);
    }

    private static int getMeltingTime() {
        int time = 0;

        while (true) {
            boolean[][] visited = new boolean[N][M];
            MELTING_CHEESE = new ArrayList<>();
            findMeltingPart(0, 0, visited);
            if (MELTING_CHEESE.isEmpty()) {
                break;
            }
            LAST_CHEESE = MELTING_CHEESE.size();
            meltCheese();
            time += 1;
        }

        return time;
    }

    private static void findMeltingPart(int row, int col, boolean[][] visited) {
        int[] dRow = {1, -1, 0, 0};
        int[] dCol = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) {
                continue;
            }

            if (visited[nextRow][nextCol]) {
                continue;
            }

            if (CHEESE[nextRow][nextCol] == 1) {
                visited[nextRow][nextCol] = true;
                MELTING_CHEESE.add(new int[]{nextRow, nextCol});
                continue;
            }

            visited[nextRow][nextCol] = true;
            findMeltingPart(nextRow, nextCol, visited);
        }
    }

    private static void meltCheese() {
        for (int[] ints : MELTING_CHEESE) {
            CHEESE[ints[0]][ints[1]] = 0;
        }
    }
}
