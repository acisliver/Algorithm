package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 인구 이동
// https://www.acmicpc.net/problem/16234
public class N16234 {

    static int N, L, R;
    static int[][] POPULATIONS;
    static boolean[][] VISITED;
    static List<List<int[]>> UNIONS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        L = input[1];
        R = input[2];
        POPULATIONS = new int[N][N];
        for (int i = 0; i < N; i++) {
            POPULATIONS[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        System.out.println(solution());
    }

    private static int solution() {
        int count = 0;

        while (true) {
            VISITED = new boolean[N][N];
            UNIONS = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (VISITED[i][j]) continue;
                    findUnion(i, j);
                }
            }

            if (UNIONS.size() == 0) return count;

            movePeople();
            count += 1;
        }

    }

    private static void findUnion(int row, int col) {

        int[] dRow = {1, -1, 0, 0};
        int[] dCol = {0 ,0, 1, -1};
        List<int[]> union = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        VISITED[row][col] = true;
        union.add(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nextRow = cur[0] + dRow[k];
                int nextCol = cur[1] + dCol[k];

                if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
                if (VISITED[nextRow][nextCol]) continue;

                int diff = Math.abs(POPULATIONS[cur[0]][cur[1]] - POPULATIONS[nextRow][nextCol]);

                if (diff < L || diff > R) continue;

                union.add(new int[]{nextRow, nextCol});
                VISITED[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }

        if (union.size() > 1) {
            UNIONS.add(union);
        }
    }

    private static void movePeople() {

        for (List<int[]> union : UNIONS) {
            int population = (int) union.stream()
                    .mapToInt(ints -> POPULATIONS[ints[0]][ints[1]])
                    .average()
                    .getAsDouble();
            for (int[] ints : union) {
                POPULATIONS[ints[0]][ints[1]] = population;
            }
        }
    }
}
