package leetcode;

import java.util.*;

// Cheapest Flights Within K Stops
public class N787 {

    public static void main(String[] args) {
        int ans = new N787().findCheapestPrice(3, new int[][]{{0,1,100},{1,2,100},{0,2,500}}, 0, 2, 0);
        System.out.println(ans);
    }


    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] flight : flights) {
            adj.computeIfAbsent(flight[0], ArrayList::new).add(new int[] {flight[1], flight[2]});
        }

        // {totalPrice, stops}
        int[] dp = new int[n];
        Arrays.fill(dp, 123456789);

        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(new int[]{src, 0});
        visited[src] = true;

        while (!queue.isEmpty() && k-- >= 0) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                int node = cur[0];
                int distance = cur[1];

                if (!adj.containsKey(node)) continue;

                for (int[] next : adj.get(node)) {
                    int neighbour = next[0];
                    int price = next[1];
                    if (price + distance >= dp[neighbour]) continue;
                    dp[neighbour] = price + distance;
                    queue.offer(new int[]{neighbour, dp[neighbour]});
                }
            }
        }

        return dp[dst] == 123456789 ? -1 : dp[dst];
    }

}
