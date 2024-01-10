package leetcode;

import java.util.*;

// Amount of Time for Binary Tree to Be Infected
// https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected
public class N2385 {
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        convertToGraph(root, graph);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        Set<Integer> visited = new HashSet<>();
        visited.add(start);

        int level = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level += 1;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();

                for (Integer next : graph.get(cur)) {
                    if (visited.contains(next)) {
                        continue;
                    }
                    queue.add(next);
                    visited.add(next);
                }
            }

        }

        return level;
    }

    public void convertToGraph(TreeNode node, Map<Integer, Set<Integer>> graph) {
        if (node == null) {
            return;
        }

        if (!graph.containsKey(node.val)) {
            graph.put(node.val, new HashSet<>());
        }

        if (node.left != null) {
            if (!graph.containsKey(node.left.val)) {
                graph.put(node.left.val, new HashSet<>());
            }
            graph.get(node.val).add(node.left.val);
            graph.get(node.left.val).add(node.val);
        }

        if (node.right != null) {
            if (!graph.containsKey(node.right.val)) {
                graph.put(node.right.val, new HashSet<>());
            }
            graph.get(node.val).add(node.right.val);
            graph.get(node.right.val).add(node.val);
        }

        convertToGraph(node.left, graph);
        convertToGraph(node.right, graph);
    }



    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
