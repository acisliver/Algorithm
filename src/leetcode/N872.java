package leetcode;

import java.util.ArrayList;
import java.util.List;

public class N872 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> seq1 = new ArrayList<>();
        List<Integer> seq2 = new ArrayList<>();

        getLeafValueSequence(root1, seq1);
        getLeafValueSequence(root2, seq2);

        System.out.println(seq1);
        System.out.println(seq2);

        if (seq1.size() != seq2.size()) {
            return false;
        }

        for (int i = 0; i < seq1.size(); i++) {
            if (seq1.get(i) != seq2.get(i)) {
                return false;
            }
        }

        return true;
    }

    private void getLeafValueSequence(TreeNode node, List<Integer> seq) {
        if (node.left == null && node.right == null) {
            seq.add(node.val);
            return;
        }

        if (node.left != null) {
            getLeafValueSequence(node.left, seq);
        }
        if (node.right != null) {
            getLeafValueSequence(node.right, seq);
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
