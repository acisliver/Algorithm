package leetcode;

// Add One Row to Tree
public class N623 {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        solve(root, val, depth);
        return root;
    }

    private void solve(TreeNode node, int val, int depth) {
        if (depth > 2) {
            if (node.left != null) {
                solve(node.left, val, depth - 1);
            }
            if (node.right != null) {
                solve(node.right, val, depth - 1);
            }
            return;
        }

        TreeNode leftSub = node.left;
        TreeNode rightSub = node.right;
        node.left = new TreeNode(val);
        node.left.left = leftSub;
        node.right = new TreeNode(val);
        node.right.right = rightSub;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
