package leetcode;

public class N404 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (isLeaf(root)) {
            return 0;
        }

        int result = 0;
        if (root.left != null) {
            if (isLeaf(root.left)) {
                result += root.left.val;
            }
            result += sumOfLeftLeaves(root.left);
        }

        if (root.right != null) {
            result += sumOfLeftLeaves(root.right);
        }

        return result;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
