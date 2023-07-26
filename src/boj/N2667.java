package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

// 단지번호붙이기
// https://www.acmicpc.net/problem/2667
public class N2667 {

    static int N;
    static int[][] MAP = new int[30][30];
    static boolean[][] VISITED = new boolean[30][30];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            MAP[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        List<Integer> apartmentCounts = countApartments();
        System.out.println(apartmentCounts.size());
        apartmentCounts.stream()
                .sorted()
                .forEach(System.out::println);
    }

    private static List<Integer> countApartments() {
        List<Integer> apartmentCounts = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (MAP[i][j] == 0) continue;
                if (VISITED[i][j]) continue;
                int apartmentCount = bfs(i, j);
                apartmentCounts.add(apartmentCount);
            }
        }
        return apartmentCounts;
    }

    private static int bfs(int row, int col) {
        if (MAP[row][col] == 0) return 0;

        final int[] dR = {0, 0, 1, -1};
        final int[] dC = {1, -1, 0, 0};

        int count = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        VISITED[row][col] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            for (int i = 0; i < 4; i++) {
                int nextRow = curRow + dR[i];
                int nextCol = curCol + dC[i];

                if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
                if (MAP[nextRow][nextCol] == 0) continue;
                if (VISITED[nextRow][nextCol]) continue;

                queue.offer(new int[]{nextRow, nextCol});
                VISITED[nextRow][nextCol] = true;
                count += 1;
            }
        }

        return count;
    }
}
