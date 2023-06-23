package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

// 최소비용 구하기
// https://www.acmicpc.net/problem/1916
public class N1916 {

    static final int MAX_INTEGER = 123456789;

    static int N, M, START, END;
    static int[] DISTANCE;
    static List<List<Edge>> EDGES = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            EDGES.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Edge edge = new Edge(input[1] - 1, input[2]);
            EDGES.get(input[0] - 1).add(edge);
        }

        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        START = input[0] - 1;
        END = input[1] - 1;

        System.out.println(solve(START, END));
    }

    private static int solve(int start, int end) {
        int[] distance = new int[N];
        Arrays.fill(distance, MAX_INTEGER);
        distance[start] = 0;

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[N];
        queue.add(new Edge(start, 0));

        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            List<Edge> nexts = EDGES.get(cur.to);

            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge next : nexts) {
                if (distance[next.to] > distance[cur.to] + next.cost) {
                    distance[next.to] = distance[cur.to] + next.cost;
                    queue.add(new Edge(next.to, distance[next.to]));
                }
            }
        }

        return distance[end];
    }

    private static class Edge implements Comparable<Edge> {

        private final int to;
        private final int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
