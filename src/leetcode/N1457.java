package leetcode;

// Pseudo-Palindromic Paths in a Binary Tree
public class N1457 {
    private int count;

    public int pseudoPalindromicPaths(TreeNode root) {
        count = 0;
        search(root, new int[10]);
        return count;
    }

    private void search(TreeNode node, int[] nums) {
        if (node.left == null && node.right == null) {
            nums[node.val] += 1;
            if (isValid(nums)) {
                count += 1;
            }

            nums[node.val] -= 1;
            return;
        }

        nums[node.val] += 1;
        if (node.left != null) {
            search(node.left, nums);
        }
        if (node.right != null) {
            search(node.right, nums);
        }
        nums[node.val] -= 1;
    }

    private boolean isValid(int[] nums) {
        boolean isMid = false;
        for (int n : nums) {
            if (n % 2 == 1) {
                if (isMid) {
                    return false;
                }
                isMid = true;
            }
        }
        return true;
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
