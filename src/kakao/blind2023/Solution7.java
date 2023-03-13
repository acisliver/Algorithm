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
//        System.out.println(Arrays.toString(
//                s.solution(new int[][]{{1, 2}, {1, 3}}, new int[]{0, 7, 3})
//        ));
    }

    static List<LinkedList<Integer>> tree = new ArrayList<>();
    static List<Integer> answer = List.of(-1);
    static int size = Integer.MAX_VALUE;
    static int[] DP = new int[10004];
    static int caseCount;

    public int[] solution(int[][] edges, int[] target) {

        for (int i = 0; i <= edges.length + 1; i++) {
            tree.add(new LinkedList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            tree.get(from).add(to);
        }

        caseCount = getCaseCount();

        for (LinkedList<Integer> list : tree) {
            list.sort(Integer::compareTo);
        }

        solve(0, target, new LinkedList<>());

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private int getCaseCount() {
        int caseCount = 1;
        for (LinkedList<Integer> nodes : tree) {
            if (nodes.size() > 0) {
                caseCount *= nodes.size();
            }
        }
        return caseCount;
    }

    private void solve(int th, int[] target, LinkedList<Integer> drops) {

        int[] numbers = {3, 2, 1};

        if (th > size) return;

        if (isEnd(target)) {
            updateAnswer(drops);
            return;
        }

        for (int number : numbers) {

            if (DP[th % caseCount] != 0) {
                target[DP[th % caseCount] - 1] -= number;
            } else {
                drop(1, number, th, target);
            }
            if (target[DP[th % caseCount] - 1] < 0) {
                target[DP[th % caseCount] - 1] += number;
                continue;
            }
            drops.add(number);
            solve(th + 1, target, drops);

            target[DP[th % caseCount] - 1] += number;
            drops.removeLast();
        }

    }

    private void drop(int curNode, int number, int th, int[] target) {
        Queue<Integer> nextNodes = tree.get(curNode);
        if (nextNodes.isEmpty()) {
            target[curNode - 1] -= number;
            DP[th] = curNode;
            return;
        }
        int nextNode = nextNodes.poll();
        nextNodes.add(nextNode);
        drop(nextNode, number, th, target);
    }

    private void updateAnswer(List<Integer> falls) {
        if (answer.get(0) == -1) {
            answer = new ArrayList<>(falls);
            size = answer.size();
            return;
        }

        if (answer.size() > falls.size()) {
            answer = new ArrayList<>(falls);
            size = answer.size();
            return;
        }

        if (answer.size() == falls.size()) {
            for (int i = 0; i < answer.size(); i++) {
                if (answer.get(i) > falls.get(i)) {
                    answer = new ArrayList<>(falls);
                    size = answer.size();
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
