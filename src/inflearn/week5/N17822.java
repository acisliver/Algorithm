package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 원판 돌리기
// https://www.acmicpc.net/problem/17822
public class N17822 {

    static int N, M, T;
    static int[][] DISKS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        T = input[2];
        DISKS = new int[N + 1][M];

        for (int i = 1; i <= N; i++) {
            int[] disk = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            DISKS[i] = disk;
        }

        while (T-- > 0) {
            int[] rotation = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int x = rotation[0];
            int d = rotation[1];
            int k = rotation[2];

            rotate(x, d, k);
            boolean isDuplicate = removeDuplicate();
            if (!isDuplicate) {
                update();
            }
        }

        int answer = getSum(DISKS);
        System.out.println(answer);
    }

    private static void rotate(int x, int d, int k) {
        if (d == 0) {
            rotateClockwise(x, k);
        } else {
            rotateClockwise(x, M - k);
        }
    }

    private static void rotateClockwise(int x, int k) {

        int r = x;
        while (r <= N) {
            int[] disk = DISKS[r];
            int[] tmp = new int[M];
            int idx = 0;
            for (int i = k; i < tmp.length; i++) {
                tmp[i] = disk[idx++];
            }
            for (int i = 0; i < k; i++) {
                tmp[i] = disk[idx++];
            }
            DISKS[r] = tmp;
            r += x;
        }
    }

    private static boolean removeDuplicate() {

        int[] dI = {0, 0, 1, -1};
        int[] dJ = {1, -1, 0, 0};
        boolean isDuplicate = false;
        List<int[]> duplicates = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                int num = DISKS[i][j];
                if (num == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int nI = i + dI[k];
                    int nJ = (M + j + dJ[k]) % M;

                    if (nI <= 0 || nI > N) continue;

                    int adjNum = DISKS[nI][nJ];
                    if (num == adjNum) {
                        duplicates.add(new int[]{nI, nJ});
                    }
                }
            }
        }

        duplicates.forEach(ij -> DISKS[ij[0]][ij[1]] = 0);

        return !duplicates.isEmpty();
    }

    private static void update() {

        int sum = 0;
        int count = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (DISKS[i][j] > 0) {
                    sum += DISKS[i][j];
                    count += 1;
                }
            }
        }

        double avg = (double) sum / count;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (DISKS[i][j] == 0) continue;
                if (DISKS[i][j] > avg) {
                    DISKS[i][j] -= 1;
                } else if (DISKS[i][j] < avg) {
                    DISKS[i][j] += 1;
                }
            }
        }
    }

    private static int getSum(int[][] disks) {

        int sum = 0;

        for (int[] disk : disks) {
            for (int i : disk) {
                sum += i;
            }
        }

        return sum;
    }
}
