package groom.week2;

import java.io.*;
import java.util.Arrays;

public class Main4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        int answer = 0;

        for (int i = 0; i < input[1]; i++) {
            int[] where = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf)
                    .toArray();
            answer += bomb(where[0], where[1], input[0]);
        }

        System.out.println(answer);
    }

    private static int bomb(int i, int j, int n) {
        int result = 1;

        int[][] dirs = new int[][]{
                {i, j - 1},
                {i, j + 1},
                {i - 1, j},
                {i + 1, j}
        };

        for (int[] dir : dirs) {
            if (dir[0] < 1 || dir[1] < 1 || dir[0] > n || dir[1] > n) continue;
            result += 1;
        }

        return result;
    }
}
