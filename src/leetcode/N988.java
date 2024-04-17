package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Smallest String Starting From Leaf
// lexicographically smallest
// 1. 길이 -> 백트래킹으로 가지치기
// 2. 알파벳 순서 -> 두 sb 비교 필요
public class N988 {

    List<Integer> answer;

    public String smallestFromLeaf(TreeNode root) {
        answer = new ArrayList<>();
        solution(root, new ArrayList<>());
        return answer.stream()
                .map(i -> Character.toString((char) ('a' + i)))
                .collect(Collectors.joining());
    }

    private void solution(TreeNode node, List<Integer> candidate) {
        if (candidate.size() > answer.size()) {
            return;
        }

        if (node == null) {
            answer = getAnswer(candidate);
            return;
        }

        candidate.add(0, node.val);
        solution(node.left, candidate);
        candidate.remove(0);
        candidate.add(0, node.val);
        solution(node.right, candidate);
        candidate.remove(0);
    }

    private List<Integer> getAnswer(List<Integer> candidate) {
        if (answer.isEmpty()) {
            return candidate;
        }

        if (candidate.size() > answer.size()) {
            return answer;
        }

        if (candidate.size() < answer.size()) {
            return candidate;
        }

        for (int i = 0; i < candidate.size(); i++) {
            int a = answer.get(i);
            int c = candidate.get(i);
            if (a > c) {
                return candidate;
            }
            if (c > a) {
                return answer;
            }
        }
        return answer;
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
