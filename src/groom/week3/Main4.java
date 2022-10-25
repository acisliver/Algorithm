package groom.week3;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main4 {

    static Set<Integer> cycle;
    static int prevNode;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<List<Integer>> graph = new ArrayList<>();
        cycle = new TreeSet<>();
        prevNode = -2;

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n + 1];
        search(1, 1, visited, graph);

        System.out.printf("%s\n%s", cycle.size(), cycle.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static void search(int startNode, int parentNode, boolean[] visited, List<List<Integer>> graph) {

        if (visited[startNode]) {   // 사이클 발생
            prevNode = startNode;
            cycle.add(startNode);
            return;
        }

        visited[startNode] = true;

        for (Integer nextNode : graph.get(startNode)) { // 인접한 노드 방문

            if (nextNode == parentNode) continue;         // 물을 공급해준 노드일 경우 탐색을 정지

            search(nextNode, startNode, visited, graph);

            if (prevNode == -1) return;     // 이미 발생한 순환 노드의 경우

            if (prevNode == startNode) {
                prevNode = -1;
                return;
            }

            if (prevNode >= 0) {
                cycle.add(startNode);
                return;
            }
        }
    }

}
