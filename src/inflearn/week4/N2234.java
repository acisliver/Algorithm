package inflearn.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 성곽
// https://www.acmicpc.net/problem/2234
/*
    1. 비트마스킹으로 움직일 수 있는 방향 결정하기
    2. VISITED로 방의 개수 구하기
    2. BFS로 각 방의 넓비 구하고 최댓값 구하기
    3. 인접한 방끼리 합쳐서 최댓값 구하기
 */
public class N2234 {

    static int N, M, ROOM, MAX_AREA, CRASHED_MAX_AREA;
    static int[][] CASTLE;
    static int[][] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        CASTLE = new int[M][N];
        VISITED = new int[M][N];
        int[] roomSize = new int[N * M + 1];

        for (int i = 0; i < M; i++) {
            CASTLE[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int roomNumber = 1;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (VISITED[i][j] != 0) continue;
                ROOM += 1;
                int area = bfs(i, j, roomNumber);
                roomSize[roomNumber] = area;
                MAX_AREA = Math.max(MAX_AREA, area);
                roomNumber += 1;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i + 1 < M) {
                    int room1 = VISITED[i][j];
                    int room2 = VISITED[i + 1][j];
                    if (room1 != room2) {
                        CRASHED_MAX_AREA = Math.max(CRASHED_MAX_AREA, roomSize[room1] + roomSize[room2]);
                    }
                }
                if (j + 1 < N) {
                    int room1 = VISITED[i][j];
                    int room2 = VISITED[i][j + 1];
                    if (room1 != room2) {
                        CRASHED_MAX_AREA = Math.max(CRASHED_MAX_AREA, roomSize[room1] + roomSize[room2]);
                    }
                }
            }
        }


        System.out.println(ROOM);
        System.out.println(MAX_AREA);
        System.out.println(CRASHED_MAX_AREA);
    }

    private static int bfs(int i, int j, int roomNumber) {

        int[] direct = {1, 2, 4, 8};
        int[] dR = {0, -1, 0, 1};
        int[] dC = {-1, 0, 1, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        VISITED[i][j] = roomNumber;

        int area = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curI = cur[0];
            int curJ = cur[1];

            for (int k = 0; k < 4; k++) {
                if ((CASTLE[curI][curJ] & direct[k]) == direct[k]) continue;

                int nI = curI + dR[k];
                int nJ = curJ + dC[k];

                if (VISITED[nI][nJ] != 0) continue;
                queue.offer(new int[]{nI, nJ});
                VISITED[nI][nJ] = roomNumber;
                area += 1;
            }
        }

        return area;
    }
}
