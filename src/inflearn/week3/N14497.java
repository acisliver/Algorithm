package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 주난의 난(難)
//https://www.acmicpc.net/problem/14497
public class N14497 {

    static int r, c;
    static char[][] classRoom;
    static int[] joonan, choco;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        r = input[0];
        c = input[1];
        classRoom = new char[r][c];
        input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        joonan = new int[]{input[0] - 1, input[1] - 1};
        choco = new int[]{input[2] - 1, input[2] - 1};

        for (int i = 0; i < r; i++) {
            classRoom[i] = br.readLine().toCharArray();
        }

        boolean isEnd;
        int count = 0;

        do {
            isEnd = jump();
            count += 1;
        } while (!isEnd);

        System.out.println(count);
    }

    private static boolean jump() {
        int[] dRow = {1, -1, 0, 0};
        int[] dCol = {0, 0, 1, -1};

        boolean isReachChoco = false;
        boolean[][] visited = new boolean[r][c];
        visited[joonan[0]][joonan[1]] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{joonan[0], joonan[1]});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dRow[i];
                int nextC = cur[1] + dCol[i];

                if (nextR < 0 || nextC < 0 || nextR >= r || nextC >= c) continue;
                if (visited[nextR][nextC]) continue;
                if (classRoom[nextR][nextC] == '1') {
                    visited[nextR][nextC] = true;
                } else if (classRoom[nextR][nextC] == '0') {
                    visited[nextR][nextC] = true;
                    queue.offer(new int[]{nextR, nextC});
                } else if (classRoom[nextR][nextC] == '#') {
                    isReachChoco = true;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (visited[i][j]) {
                    classRoom[i][j] = '0';
                }
            }
        }

        return isReachChoco;
    }
}
