package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.in;


// 트리의 지름
// https://www.acmicpc.net/problem/1967
public class N1967 {
    static int V, L, MAX;
    static List<List<int[]>> TREE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        V = Integer.parseInt(br.readLine());
        L = 0;
        MAX = 0;
        TREE = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            TREE.add(new ArrayList<>());
        }
        for (int i = 0; i < V - 1; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = input[0] - 1;
            int to = input[1] - 1;
            int distance = input[2];
            TREE.get(from).add(new int[]{to, distance});
            TREE.get(to).add(new int[]{from, distance});
        }

        boolean[] visited = new boolean[V];
        visited[0] = true;
        search(0, visited, 0);

        visited = new boolean[V];
        visited[L] = true;
        search(L, visited, 0);

        System.out.println(MAX);
    }

    private static void search(int cur, boolean[] visited, int totalDistance) {
        if (totalDistance > MAX) {
            MAX = totalDistance;
            L = cur;
        }

        List<int[]> nexts = TREE.get(cur);
        for (int[] next : nexts) {
            int to = next[0];
            int distance = next[1];
            if (visited[to]) continue;
            visited[to] = true;
            search(to, visited, totalDistance + distance);
        }
    }
}
