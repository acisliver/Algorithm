package groom.week3;

import java.io.*;
import java.util.*;

public class Main3 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);
        int k = Integer.parseInt(input1[2]);

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
             graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        System.out.println(bfs(k, graph) ? "YES" : "NO");
    }

    private static boolean bfs(int k, List<List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();

        int n = graph.size() - 1;
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);
        distance[1] = 0;

        queue.add(1);

        while(!queue.isEmpty()) {

            int cur = queue.poll();

            for (Integer node : graph.get(cur)) {
                if (distance[node] != -1) continue;

                queue.add(node);
                distance[node] = distance[cur] + 1;
            }
        }

        return distance[n] != -1 && distance[n] <= k;
    }

}
