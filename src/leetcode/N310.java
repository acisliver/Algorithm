package leetcode;

import java.util.*;

// Minimum Height Trees
public class N310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> answer = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        int minHeight = n + 1;
        for (int i = 0; i < n; i++) {
            int height = search(graph, i, n);
            if (height < minHeight) {
                minHeight = height;
                answer.clear();
                answer.add(i);
            } else if (height == minHeight){
                answer.add(i);
            }
        }

        return answer;
    }

    private int search(Map<Integer, List<Integer>> graph, int start, int n) {
        int h = 0;
        boolean[] visited = new boolean[n];
        visited[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            h += 1;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                List<Integer> nexts = graph.get(cur);
                for (Integer next : nexts) {
                    if (visited[next]) {
                        continue;
                    }
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }

        return h;
    }
}
