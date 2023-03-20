package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 배열 돌리기 4
// https://www.acmicpc.net/problem/17406
public class N17406 {

    static int N, M, K, ANSWER;
    static int[][] ARR;
    static List<int[]> COMMAND;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        K = input[2];
        ANSWER = Integer.MAX_VALUE;
        ARR = new int[N][M];
        for (int i = 0; i < N; i++) {
            ARR[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        COMMAND = new ArrayList<>();
        while (K-- > 0) {
            input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int r = input[0] - 1;
            int c = input[1] - 1;
            int s = input[2];
            COMMAND.add(new int[]{r, c, s});
        }

        dfs(new boolean[COMMAND.size()]);

        System.out.println(ANSWER);
    }

    private static void dfs(boolean[] visited) {
        if (isEnd(visited)) {
            ANSWER = Math.min(ANSWER, getA());
            return;
        }

        for (int i = 0; i < COMMAND.size(); i++) {
            if (visited[i]) continue;

            int[] command = COMMAND.get(i);
            int r = command[0];
            int c = command[1];
            int s = command[2];

            int[][] temp = getClone();
            visited[i] = true;

            rotate(r, c, s);

            dfs(visited);

            ARR = temp;
            visited[i] = false;
        }
    }

    private static boolean isEnd(boolean[] visited) {
        for (boolean v : visited) {
            if (!v) return false;
        }

        return true;
    }

    private static int[][] getClone() {
        int[][] clone = new int[N][M];

        for (int i = 0; i < N; i++) {
            clone[i] = ARR[i].clone();
        }

        return clone;
    }

    private static void rotate(int r, int c, int s) {

        if (s == 0) return;

        int[] dR = {0, 1, 0, -1};
        int[] dC = {1, 0, -1, 0};

        int topLeftR = r - s;
        int topLeftC = c - s;
        int bottomRightR = r + s;
        int bottomRightC = c + s;

        int row = topLeftR;
        int col = topLeftC;

        int idx = 0;
        int temp = 0;
        int prev = ARR[row][col];
        while (idx < 4) {
            int nR = row + dR[idx];
            int nC = col + dC[idx];

            if (nR < topLeftR || nC < topLeftC || nR > bottomRightR || nC > bottomRightC) {
                idx++;
                continue;
            }

            temp = ARR[nR][nC];
            ARR[nR][nC] = prev;
            prev = temp;
            row = nR;
            col = nC;
        }

        rotate(r, c, s - 1);
    }

    private static int getA() {
        int a = Integer.MAX_VALUE;

        for (int[] ints : ARR) {
            int sum = Arrays.stream(ints).sum();
            a = Math.min(a, sum);
        }

        return a;
    }
}
