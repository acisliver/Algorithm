package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 미세먼지 안녕!
// https://www.acmicpc.net/problem/17144
public class N17144 {

    static int R, C ,T;
    static int[][] MAP;
    static List<int[]> AIR_CLEANER;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        R = input[0];
        C = input[1];
        T = input[2];
        MAP = new int[R][C];
        AIR_CLEANER = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            MAP[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < C; j++) {
                if (MAP[i][j] == -1) {
                    AIR_CLEANER.add(new int[]{i, j});
                }
            }
        }
    }

    private static void diffuseDust() {

    }
}
