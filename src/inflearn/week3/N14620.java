package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 꽃길
// https://www.acmicpc.net/problem/14620
public class N14620 {

    static int N, COST, MIN_COST;
    static int[][] FLOWER_BED_COST;
    static boolean[][] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        MIN_COST = Integer.MAX_VALUE;
        FLOWER_BED_COST = new int[N][N];
        VISITED = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            FLOWER_BED_COST[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        calculateMinCost(3, 0);

        System.out.println(MIN_COST);
    }

    private static boolean canBlossom(int row, int col) {

        int[] dRow = {0, 1, -1, 0, 0};
        int[] dCol = {0, 0, 0, 1, -1};

        for (int i = 0; i < 5; i++) {
            int nR = row + dRow[i];
            int nC = col + dCol[i];

            if (nR < 0 || nC < 0 || nR >= N || nC >= N) return false;
            if (VISITED[nR][nC]) return false;
        }

        return true;
    }

    private static int calculateCost(int row, int col) {
        int[] dRow = {0, 1, -1, 0, 0};
        int[] dCol = {0, 0, 0, 1, -1};
        int cost = 0;

        for (int i = 0; i < 5; i++) {
            int nR = row + dRow[i];
            int nC = col + dCol[i];

            VISITED[nR][nC] = true;
            cost += FLOWER_BED_COST[nR][nC];
        }

        return cost;
    }

    private static void eraseFlower(int row, int col) {
        int[] dRow = {0, 1, -1, 0, 0};
        int[] dCol = {0, 0, 0, 1, -1};

        for (int i = 0; i < 5; i++) {
            int nR = row + dRow[i];
            int nC = col + dCol[i];

            VISITED[nR][nC] = false;
        }
    }

    private static void calculateMinCost(int count, int cost) {
        if (count == 0) {
            MIN_COST = Math.min(MIN_COST, cost);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!canBlossom(i, j)) continue;
                calculateMinCost(count - 1, cost + calculateCost(i, j));
                eraseFlower(i, j);
            }
        }
    }


}
