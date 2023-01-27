package inflearn.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 경사로
// https://www.acmicpc.net/problem/14890
/*
    1. 각 행, 열에 대하여 탐색을 진행 -> 지도를 뒤집은 새로운 지도 생성
    2. 같은 높이의 경우 경사로를 놓을 수 있는 자리 수(count)를 1개 늘린다.
    3. 1칸 높은 경우 길이가 L인 경사로를 놓을 수 있는지 확인한다. (L <= count)
    4. 1칸 낮은 경우 우선 경사로를 놓고 후에 탐색에서 결정한다. (count = -L + 1)
    5. 탐색 중 혹은 탐색이 끝났을 때 count가 음수라면 경사로를 놓을 수 없는 경우에 경사로를 놓은 것이다.
 */
public class N14890 {

    static int N, L;
    static int[][] MAP, ROTATED_MAP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        L = input[1];
        MAP = new int[N][N];
        ROTATED_MAP = new int[N][N];

        for (int i = 0; i < N; i++) {
            MAP[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < N; j++) {
                ROTATED_MAP[j][i] = MAP[i][j];
            }
        }

        System.out.println(solve(MAP) + solve(ROTATED_MAP));
    }

    private static int solve(int[][] map) {

        int availableRoadCount = 0;

        for (int i = 0; i < N; i++) {
            int count = 1;
            boolean isAvailable = true;

            for (int j = 0; j < N - 1; j++) {
                if (map[i][j] == map[i][j + 1]) {
                    count += 1;
                } else if (map[i][j] + 1 == map[i][j + 1]) {
                    if (count < L) {
                        isAvailable = false;
                        break;
                    }
                    count = 1;
                } else if (map[i][j] - 1 == map[i][j + 1]) {
                    if (count < 0) {
                        isAvailable = false;
                        break;
                    }
                    count = -L + 1;
                } else {
                    isAvailable = false;
                    break;
                }
            }

            if (isAvailable && count >= 0) availableRoadCount += 1;
        }

        return availableRoadCount;
    }
}
