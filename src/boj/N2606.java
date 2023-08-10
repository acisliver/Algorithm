package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.in;

// 바이러스
// https://www.acmicpc.net/problem/2606
public class N2606 {

    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            graph.add(new LinkedList<>());
        }
        for (int i = 0; i < M; i++) {
            int[] ints = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = ints[0] - 1;
            int to = ints[1] - 1;
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        boolean[] visited = new boolean[N];
        visited[0] = true;
        dfs(0, visited);
        System.out.println(ANSWER);
    }

    private static void dfs(int from, boolean[] visited) {
        for (Integer to : graph.get(from)) {
            if (visited[to]) continue;
            visited[to] = true;
            ANSWER += 1;
            dfs(to, visited);
        }
    }
}
