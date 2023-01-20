package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 컴백홈
// https://www.acmicpc.net/problem/1189
public class N1189 {

    static int r, c, k, totalCount;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        r = input[0];
        c = input[1];
        k = input[2];
        totalCount = 0;
        map = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        visited[r - 1][0] = true;
        search(r - 1, 0, 1);

        System.out.println(totalCount);
    }

    private static void search(int row, int col, int count) {
        if (count > k) return;

        if (row == 0 && col == c - 1) {
            if (count == k) {
                totalCount += 1;
            }
            return;
        }

        int[] dRow = {1, -1, 0, 0};
        int[] dCol = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int nR = row + dRow[i];
            int nC = col + dCol[i];

            if (nR < 0 || nC < 0 || nR >= r || nC >= c) continue;
            if (map[nR][nC] == 'T') continue;
            if (visited[nR][nC]) continue;

            visited[nR][nC] = true;
            search(nR, nC, count + 1);
            visited[nR][nC] = false;
        }
    }
}
