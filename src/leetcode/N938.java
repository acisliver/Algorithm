package leetcode;

// Range Sum of BST
// https://leetcode.com/problems/range-sum-of-bst
public class N938 {

    public int rangeSumBST(TreeNode root, int low, int high) {
        return search(root, low, high);
    }

    private int search(TreeNode node, int low, int high) {

        if (node == null) {
            return 0;
        }

        int result = 0;

        if (node.val >= low && node.val <= high) {
            result += node.val;
            result += search(node.left, low, high) + search(node.right, low, high);
        } else if (node.val < low) {
            result += search(node.right, low, high);
        } else {
            result += search(node.left, low, high);
        }

        return result;
    }

    static class TreeNode {
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
