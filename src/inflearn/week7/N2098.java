package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 외판원 순회
// https://www.acmicpc.net/problem/2098
public class N2098 {

    static int visitAll;
    static int[][] GRAPH, DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        GRAPH = new int[n][n];
        for (int i = 0; i < n; i++) {
            GRAPH[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        visitAll = (1 << n) - 1;
        DP = new int[n][visitAll + 1];
        System.out.println(tsp(0, 1));
    }

    private static int tsp(int cur, int visited) {
        if (DP[cur][visited] != 0) return DP[cur][visited];
        if (visited == visitAll) {
            // cur에서 다시 출발점으로 돌아와야함
            if (GRAPH[cur][0] == 0) return 987654321;
            else return GRAPH[cur][0];
        }

        int min = 987654321;

        for (int i = 1; i < DP.length; i++) {
            if (GRAPH[cur][i] == 0) continue;
            if ((visited & (1 << i)) != 0) continue;
            min = Math.min(tsp(i, visited | (1 << i)) + GRAPH[cur][i], min);
        }

        DP[cur][visited] = min;
        return DP[cur][visited];
    }
}
