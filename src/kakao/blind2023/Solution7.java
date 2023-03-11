package kakao.blind2023;

import java.util.*;

// 1,2,3 떨어뜨리기
// https://school.programmers.co.kr/learn/courses/30/lessons/150364
public class Solution7 {
    public static void main(String[] args) {
        Solution7 s = new Solution7();
        System.out.println(Arrays.toString(s.solution(new int[][]{
                        {2, 4}, {1, 2}, {6, 8}, {1, 3}, {5, 7}, {2, 5}, {3, 6}, {6, 10}, {6, 9}
                }, new int[]{0, 0, 0, 3, 0, 0, 5, 1, 2, 3}
        )));
        System.out.println(Arrays.toString(
                s.solution(new int[][]{{1, 2}, {1, 3}}, new int[]{0, 7, 3})
        ));
    }

    static List<List<Integer>> tree = new ArrayList<>();
    static List<Integer> answer = List.of(-1);
    static List<Integer> DP = new ArrayList<>();

    public int[] solution(int[][] edges, int[] target) {

        for (int i = 0; i <= edges.length + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            tree.get(from).add(to);
        }

        for (List<Integer> list : tree) {
            list.sort(Integer::compareTo);
        }

        search(1, target, new LinkedList<>());

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private void search(int curNode, int[] target, List<Integer> falls) {

        if (isEnd(target)) {
            updateAnswer(falls);
            return;
        }

        for (Integer nextNode : tree.get(curNode)) {

        }

        falls.add(1);
        falls.add(2);
        falls.add(3);
    }

    private void updateAnswer(List<Integer> falls) {
        if (answer.get(0) == -1) {
            answer = new ArrayList<>(falls);
            return;
        }

        if (answer.size() > falls.size()) {
            answer = new ArrayList<>(falls);
            return;
        }

        if (answer.size() == falls.size()) {
            for (int i = 0; i < answer.size(); i++) {
                if (answer.get(i) > falls.get(i)) {
                    answer = new ArrayList<>(falls);
                    return;
                } else if (answer.get(i) < falls.get(i)) {
                    return;
                }
            }
        }
    }

    private boolean isEnd(int[] target) {
        for (int i : target) {
            if (i > 0) return false;
        }

        return true;
    }
}
