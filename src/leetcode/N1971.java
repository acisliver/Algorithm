package leetcode;

import java.util.ArrayList;
import java.util.List;

// Find if Path Exists in Graph
// 오름차순으로만 노드를 방문한다면 사이클이 생기지 않는다.
public class N1971 {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        return solve(graph, new boolean[n], source, destination);
    }

    private boolean solve(List<List<Integer>> edges, boolean[] visited, int source ,int destination) {
        if (source == destination) {
            return true;
        }

        boolean result = false;
        List<Integer> nexts = edges.get(source);
        for (int next : nexts) {
            if (visited[next]) {
                continue;
            }
            visited[next] = true;
            result |= solve(edges, visited, next, destination);
        }

        return result;
    }
}
