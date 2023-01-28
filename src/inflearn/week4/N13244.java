package inflearn.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Tree
// https://www.acmicpc.net/problem/13244
public class N13244 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int node = Integer.parseInt(br.readLine());
            int edge = Integer.parseInt(br.readLine());
            boolean[][] tree = new boolean[node + 1][node + 1];
            boolean isTree = true;

            for (int i = 0; i < edge; i++) {
                String[] input = br.readLine().split(" ");
                int from = Integer.parseInt(input[0]);
                int to = Integer.parseInt(input[1]);
                if (tree[from][to]) {
                    isTree = false;
                    break;
                }
                tree[from][to] = true;
                tree[to][from] = true;
            }

            boolean[] visited = new boolean[node + 1];
            visited[0] = true;
            search(1, tree, visited);
            if (isTree && isConnected(visited)) {
                System.out.println("tree");
            } else {
                System.out.println("graph");
            }
        }
    }

    private static void search(int curNode, boolean[][] tree, boolean[] visited) {
        visited[curNode] = true;
        boolean[] nextNodes = tree[curNode];
        for (int i = 1; i < nextNodes.length; i++) {
            boolean nextNode = nextNodes[i];
            if (!nextNode) continue;
            if (visited[i]) continue;
            search(i, tree, visited);
        }
    }

    private static boolean isConnected(boolean[] visited) {
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }
}
