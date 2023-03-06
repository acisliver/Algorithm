package inflearn.week2;

import java.io.*;
import java.util.*;

// 효율적인 해킹
// https://www.acmicpc.net/problem/1325
public class N1325 {

    static int N, M;
    static List<List<Integer>> GRAPH = new ArrayList<>();
    static int[] COUNTS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            GRAPH.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            GRAPH.get(b).add(a);
        }

        COUNTS = new int[N + 1];
        int max = 0;
        for (int i = 1; i <= N; i++) {
            bfs(i);
            max = Math.max(COUNTS[i], max);
        }

        for (int i = 1; i <= N; i++) {
            if (COUNTS[i] == max) bw.write(i + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(n);
        visited[n] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Integer next : GRAPH.get(cur)) {
                if (visited[next]) continue;
                queue.add(next);
                visited[next] = true;
                COUNTS[n] += 1;
            }
        }
    }
}
