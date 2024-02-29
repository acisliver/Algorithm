package leetcode;

import java.util.LinkedList;
import java.util.Queue;

// Even Odd Tree
public class N1609 {

    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int prev;
            if (level % 2 == 1) {
                prev = 1000001;
            } else {
                prev = 0;
            }
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (level % 2 == 0) {
                    if (cur.val % 2 == 0) {
                        System.out.println(cur.val);
                        return false;
                    }
                    if (cur.val <= prev) {
                        System.out.println(prev);
                        System.out.println(cur.val);
                        return false;
                    }
                } else {
                    if (cur.val % 2 != 0) {
                        System.out.println(cur.val);
                        return false;
                    }
                    if (cur.val >= prev) {
                        System.out.println(prev);
                        return false;
                    }
                }
                prev = cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            level += 1;
        }

        return true;
    }

    private static class TreeNode {
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
