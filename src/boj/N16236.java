package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

// 아기 상어
// https://www.acmicpc.net/problem/16236
public class N16236 {

    static int N, SIZE, EATEN;
    static int[][] SPACE;
    static int[] SHARK;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        N = Integer.parseInt(br.readLine());
        SIZE = 2;
        EATEN = 0;
        SPACE = new int[N][N];
        for (int i = 0; i < N; i++) {
            SPACE[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < N; j++) {
                if (SPACE[i][j] == 0) continue;
                if (SPACE[i][j] == 9) {
                    SHARK = new int[]{i, j};
                    SPACE[i][j] = 0;
                }
            }
        }

        int answer = solve(SHARK[0], SHARK[1]);
        System.out.println(answer);
    }

    private static int solve(int i, int j) {

        int seconds = 0;
        int sharkI = i;
        int sharkJ = j;

        while (true) {
            PriorityQueue<int[]> fishes = getFishes(sharkI, sharkJ);

            if (fishes.isEmpty()) {
                return seconds;
            }

            int[] fish = fishes.poll();
            sharkI = fish[0];
            sharkJ = fish[1];
            SPACE[sharkI][sharkJ] = 0;
            seconds += fish[2];
            EATEN += 1;
            if (EATEN == SIZE) {
                SIZE += 1;
                EATEN = 0;
            }
        }

    }

    private static PriorityQueue<int[]> getFishes(int i, int j) {
        final int[] dI = {0, 0, 1, -1};
        final int[] dJ = {1, -1, 0, 0};

        int maxDistance = Integer.MAX_VALUE;
        PriorityQueue<int[]> canEatFishes = new PriorityQueue<>((o1, o2) -> {
            int distance = o1[2] - o2[2];
            if (distance != 0) return distance;

            int row = o1[0] - o2[0];
            if (row == 0) {
                return o1[1] - o2[1];
            }
            return row;
        });

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        visited[i][j] = true;
        queue.add(new int[]{i, j, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curI = cur[0];
            int curJ = cur[1];
            int distance = cur[2];

            for (int k = 0; k < 4; k++) {
                int nI = curI + dI[k];
                int nJ = curJ + dJ[k];

                if (nI < 0 || nJ < 0 || nI >= N || nJ >= N) continue;
                if (visited[nI][nJ]) continue;

                int fish = SPACE[nI][nJ];
                if (fish > SIZE) continue;

                if (fish == SIZE || fish == 0) {
                    queue.offer(new int[]{nI, nJ, distance + 1});
                    visited[nI][nJ] = true;
                    continue;
                }

                canEatFishes.add(new int[]{nI, nJ, distance + 1});
                maxDistance = distance + 1;
            }
        }

        return canEatFishes;
    }
}
