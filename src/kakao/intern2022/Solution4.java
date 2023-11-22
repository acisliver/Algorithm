package kakao.intern2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/118669?language=java
// Dijkstra
public class Solution4 {
    public static void main(String[] args) {
        Solution4 s = new Solution4();
        System.out.println(Arrays.toString(s.solution(7,
                new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}},
                new int[]{1, 3},
                new int[]{5})));
        System.out.println(Arrays.toString(s.solution(7,
                new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}},
                new int[]{1},
                new int[]{2,3,4})));
    }

    private static final int INF = 123456789;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Set<Integer> isGate = new HashSet<>();
        Set<Integer> isSummit = new HashSet<>();
        List<List<Path>> graph = new ArrayList<>();

        for (int gate : gates) {
            isGate.add(gate);
        }

        for (int summit : summits) {
            isSummit.add(summit);
        }

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int w = path[2];

            if (!isSummit.contains(from) && !isGate.contains(to)) {
                graph.get(from).add(new Path(to, w));
            }

            if (!isSummit.contains(to) && !isGate.contains(from)) {
                graph.get(to).add(new Path(from, w));
            }
        }

        int[] dp = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(dp, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int gate : gates) {
            dp[gate] = 0;
            pq.add(new Node(gate, 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            visited[cur.node] = true;
            if (isSummit.contains(cur.node)) continue;  // 가지치기: 정상에서 나가는 간선은 없음
            if (cur.w > dp[cur.node]) continue;         // 가지치기: 현재 노드까지의 가중치가 이미 최소로 갱신된 상태라 이전에 갱신했던 경우는 필요 없음
            for (Path next : graph.get(cur.node)) {
                if (visited[next.to]) continue;
                int intensity = Math.max(dp[cur.node], next.w);
                dp[next.to] = Math.min(dp[next.to], intensity);
                pq.offer(new Node(next.to, next.w));
            }
        }

        int minIntensity = INF;
        int goal = 123456789;
        for (int summit : summits) {
            if (dp[summit] == INF) continue;
            if (minIntensity == dp[summit]) {
                goal = Math.min(goal, summit);
            }
            if (minIntensity > dp[summit]) {
                minIntensity = dp[summit];
                goal = summit;
            }
        }

        return new int[]{goal, minIntensity};
    }

    static class Path {
        int to;
        int w;

        public Path(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    static class Node implements Comparable<Node> {
        int node;
        int w;


        public Node(int node, int w) {
            this.node = node;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
