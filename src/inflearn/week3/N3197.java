package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백조의 호수
// https://www.acmicpc.net/problem/3197
public class N3197 {

    static int r, c;
    static char[][] lake;
    static boolean[][] visited, swanVisited;
    static Queue<int[]> waterQueue, waterTempQueue, swanQueue, swanTempQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        r = input[0];
        c = input[1];
        lake = new char[r][c];
        int[] swans = new int[2];
        swanVisited = new boolean[r][c];
        visited = new boolean[r][c];
        waterQueue = new LinkedList<>();
        waterTempQueue = new LinkedList<>();
        swanQueue = new LinkedList<>();
        swanTempQueue = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            lake[i] = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (lake[i][j] == 'L') {
                    swans = new int[]{i, j};
                    waterQueue.offer(new int[]{i, j});
                    visited[i][j] = true;
                } else if (lake[i][j] == '.') {
                    waterQueue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        swanQueue.offer(swans);
        swanVisited[swans[0]][swans[1]] = true;

        int count = 0;

        while (!isMet()) {
            melting();
            waterQueue = waterTempQueue;
            waterTempQueue = new LinkedList<>();
            swanQueue = swanTempQueue;
            swanTempQueue = new LinkedList<>();
            count += 1;
        }

        System.out.println(count);
    }

    private static void melting() {

        int[] dRow = {1, -1, 0, 0};
        int[] dCol = {0, 0, 1, -1};

        while (!waterQueue.isEmpty()) {
            int[] cur = waterQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dRow[i];
                int nextC = cur[1] + dCol[i];

                if (nextR < 0 || nextC < 0 || nextR >= r || nextC >= c) continue;
                if (visited[nextR][nextC]) continue;

                if (lake[nextR][nextC] == 'X') {
                    visited[nextR][nextC] = true;
                    lake[nextR][nextC] = '.';
                    waterTempQueue.offer(new int[]{nextR, nextC});
                }
            }
        }

    }

    private static boolean isMet() {
        int[] dRow = {1, -1, 0, 0};
        int[] dCol = {0, 0, 1, -1};

        while (!swanQueue.isEmpty()) {
            int[] cur = swanQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dRow[i];
                int nextC = cur[1] + dCol[i];

                if (nextR < 0 || nextC < 0 || nextR >= r || nextC >= c) continue;
                if (swanVisited[nextR][nextC]) continue;

                swanVisited[nextR][nextC] = true;
                if (lake[nextR][nextC] == '.') {
                    swanQueue.offer(new int[]{nextR, nextC});
                } else if (lake[nextR][nextC] == 'X') {
                    swanTempQueue.offer(new int[]{nextR, nextC});
                } else if (lake[nextR][nextC] == 'L') {
                    return true;
                }
            }
        }

        return false;
    }
}
