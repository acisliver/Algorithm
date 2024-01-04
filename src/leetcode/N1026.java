package leetcode;

import java.util.ArrayList;
import java.util.List;

public class N1026 {

    int answer = 0;

    public int maxAncestorDiff(TreeNode root) {
        calc(root, List.of(root.val));
        return answer;
    }

    private void calc(TreeNode node, List<Integer> ancestors) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            for (Integer ancestor : ancestors) {
                answer = Math.max(Math.abs(ancestor - node.left.val), answer);
            }
            List<Integer> copy = new ArrayList<>(ancestors);
            copy.add(node.left.val);
            calc(node.left, copy);
        }

        if (node.right != null) {
            for (Integer ancestor : ancestors) {
                answer = Math.max(Math.abs(ancestor - node.right.val), answer);
            }
            List<Integer> copy = new ArrayList<>(ancestors);
            copy.add(node.right.val);
            calc(node.right, copy);
        }
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
