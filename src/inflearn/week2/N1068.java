package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 트리
// https://www.acmicpc.net/problem/1068
public class N1068 {

    static int N, ERASE_NODE, CHILD_NODE_COUNT;
    static List<List<Integer>> TREE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] parents = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        ERASE_NODE = Integer.parseInt(br.readLine());
        TREE = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            TREE.add(new LinkedList<>());
        }

        int root = 0;

        for (int node = 0; node < parents.length; node++) {
            int parent = parents[node];

            if (parent == -1) {
                root = node;
                continue;
            }

            TREE.get(parent).add(node);
        }

        if (ERASE_NODE == root) {
            System.out.println(0);
            return;
        }
        getLeafNodeCount(root);
        System.out.println(CHILD_NODE_COUNT);
    }

    private static void getLeafNodeCount(int node) {
        int child = 0;

        List<Integer> childNodes = TREE.get(node);

        for (Integer childNode : childNodes) {
            if (childNode == ERASE_NODE) {
                continue;
            }
            getLeafNodeCount(childNode);
            child += 1;
        }

        if (child == 0) CHILD_NODE_COUNT += 1;
    }
}
