package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

// 영역 구하기
// https://www.acmicpc.net/problem/2583
public class N2583 {

    static int N, M, K;
    static boolean[][] PAPER;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        PAPER = new boolean[M][N];
        int count = 0;
        List<Integer> areas = new LinkedList<>();

        for (int i = 0; i < K; i++) {
            int[] square = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            fillPaper(square);
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (PAPER[i][j]) continue;
                count += 1;
                areas.add(dfs(i, j));
            }
        }

        areas.sort(Integer::compareTo);

        System.out.println(count);
        System.out.println(areas.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static void fillPaper(int[] square) {
        int left = square[0];
        int bottom = square[1];
        int right = square[2];
        int top = square[3];

        for (int i = bottom; i < top; i++) {
            for (int j = left; j < right; j++) {
                PAPER[i][j] = true;
            }
        }
    }

    private static int dfs(int row, int col) {
        int[] dRow = {1, -1, 0, 0};
        int[] dCol = {0, 0, 1, -1};
        int area = 1;

        PAPER[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextCol < 0 || nextRow >= M || nextCol >= N) {
                continue;
            }

            if (PAPER[nextRow][nextCol]) {
                continue;
            }

            area += dfs(nextRow, nextCol);
        }

        return area;
    }
}
