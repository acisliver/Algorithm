package leetcode;

// Sum Root to Leaf Numbers
public class N129 {
    public int sumNumbers(TreeNode root) {
        return search(root, new StringBuilder());
    }

    private int search(TreeNode node, StringBuilder sb) {
        sb.append(node.val);
        if (node.left == null && node.right == null) {
            int n = Integer.parseInt(sb.toString());
            sb.deleteCharAt(sb.length() - 1);
            return n;
        }

        int result = 0;
        if (node.left != null) {
            result += search(node.left, sb);
        }
        if (node.right != null) {
            result += search(node.right, sb);
        }
        sb.deleteCharAt(sb.length() - 1);

        return result;
    }

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
