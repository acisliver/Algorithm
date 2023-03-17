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

        while (T-- > 0) {
            diffuseDust();
            clean();
        }

        System.out.println(getDustAmount());
    }

    private static void diffuseDust() {
        int[] dR = {0, 0, 1, -1};
        int[] dC = {1, -1, 0, 0};
        int[][] newMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (MAP[i][j] == -1) {
                    newMap[i][j] = -1;
                    continue;
                }
                if (MAP[i][j] > 0) {
                    newMap[i][j] += MAP[i][j];
                    int diffuseAmount = MAP[i][j] / 5;
                    for (int k = 0; k < 4; k++) {
                        int nextR = i + dR[k];
                        int nextC = j + dC[k];

                        if (nextR < 0 || nextC < 0 || nextR >= R || nextC >= C) continue;
                        if (MAP[nextR][nextC] == -1) continue;
                        newMap[nextR][nextC] += diffuseAmount;
                        newMap[i][j] -= diffuseAmount;
                    }
                }
            }
        }

        MAP = newMap;
    }

    private static void clean() {
        int counterClockWiseCleaner = AIR_CLEANER.get(0)[0];
        int clockWiseCleaner = AIR_CLEANER.get(1)[0];

        cleanCounterClockWise(counterClockWiseCleaner);
        cleanClockWise(clockWiseCleaner);
    }

    private static void cleanCounterClockWise(int cleaner) {

        int[] dR = {0, -1, 0, 1};
        int[] dC = {1, 0, -1, 0};

        int count = 0;
        int r = cleaner;
        int c = 0;
        int prev = 0;
        int tmp = 0;

        while(true) {
            int nR = r + dR[count];
            int nC = c + dC[count];
            if (nR == cleaner && nC == 0) break;
            if (nR < 0 || nC < 0 || nR >= R || nC >= C) {
                count += 1;
                nR = r + dR[count];
                nC = c + dC[count];
            }
            if (nR == cleaner && nC == 0) break;
            tmp = MAP[nR][nC];
            MAP[nR][nC] = prev;
            prev = tmp;
            r = nR;
            c = nC;
        }
    }

    private static void cleanClockWise(int cleaner) {

        int[] dR = {0, 1, 0, -1};
        int[] dC = {1, 0, -1, 0};

        int count = 0;
        int r = cleaner;
        int c = 0;
        int prev = 0;
        int tmp = 0;

        while(true) {
            int nR = r + dR[count];
            int nC = c + dC[count];
            if (nR == cleaner && nC == 0) break;
            if (nR < 0 || nC < 0 || nR >= R || nC >= C) {
                count += 1;
                nR = r + dR[count];
                nC = c + dC[count];
            }
            if (nR == cleaner && nC == 0) break;
            tmp = MAP[nR][nC];
            MAP[nR][nC] = prev;
            prev = tmp;
            r = nR;
            c = nC;
        }
    }

    private static int getDustAmount() {
        int dustAmount = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (MAP[i][j] > 0) {
                    dustAmount += MAP[i][j];
                }
            }
        }

        return dustAmount;
    }
}
