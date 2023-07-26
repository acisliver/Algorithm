package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.System.in;

// DFS와 BFS
public class N1260 {

    static boolean[][] EDGES = new boolean[1004][1004];
    static boolean[] VISITED = new boolean[1004];
    static StringBuilder SB = new StringBuilder();
    static int N, M, V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        V = input[2];

        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int v1 = input[0];
            int v2 = input[1];
            EDGES[v1][v2] = true;
            EDGES[v2][v1] = true;
        }

        VISITED[V] = true;
        SB.append(V)
                .append(" ");
        dfs(V);
        System.out.println(SB);

        bfs(V);
    }

    private static void dfs(int cur) {

        for (int next = 1; next <= N; next++) {
            if (!EDGES[cur][next]) {
                continue;
            }

            if (VISITED[next]) {
                continue;
            }

            VISITED[next] = true;
            SB.append(next)
                    .append(" ");
            dfs(next);
        }
    }

    private static void bfs(int start) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[1004];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        sb.append(start)
                .append(" ");

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next = 1; next <= N; next++) {
                if (!EDGES[cur][next]) {
                    continue;
                }

                if (visited[next]) {
                    continue;
                }

                queue.offer(next);
                visited[next] = true;
                sb.append(next)
                        .append(" ");
            }
        }

        System.out.println(sb);
    }
}
