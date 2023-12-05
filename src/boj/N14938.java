package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 서강그라운드
// https://www.acmicpc.net/problem/14938
public class N14938 {

    static final int INF = 123456789;

    static int N, M, R;
    static int[] ITEM_COUNTS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nmr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = nmr[0];
        M = nmr[1];
        R = nmr[2];
        ITEM_COUNTS = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[][] distance = new int[N][N];
        for (int i = 0; i < distance.length; i++) {
            Arrays.fill(distance[i], INF);
        }
        for (int i = 0; i < distance.length; i++) {
            distance[i][i] = 0;
        }
        for (int i = 0; i < R; i++) {
            int[] inputs = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int a = inputs[0] - 1;
            int b = inputs[1] - 1;
            int l = inputs[2];
            distance[a][b] = l;
            distance[b][a] = l;
        }

        for (int k = 0; k < N; k++) {
            // 노드 i에서 j로 가는 경우
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 최소 비용으로 갱신
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }

        int max = -1;
        for (int i = 0; i < N; i++) {
            int itemCount = 0;
            for (int j = 0; j < distance[i].length; j++) {
                if (distance[i][j] <= M) {
                    itemCount += ITEM_COUNTS[j];
                }
            }
            max = Math.max(max, itemCount);
        }

        System.out.println(max);
    }
}
