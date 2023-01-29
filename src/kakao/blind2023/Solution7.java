package kakao.blind2023;

import java.util.*;

public class Solution7 {
    public static void main(String[] args) {
        Solution7 s = new Solution7();
//        System.out.println(Arrays.toString(s.solution(new int[][]{
//                        {2, 4}, {1, 2}, {6, 8}, {1, 3}, {5, 7}, {2, 5}, {3, 6}, {6, 10}, {6, 9}
//                }, new int[]{0, 0, 0, 3, 0, 0, 5, 1, 2, 3}
//        )));
        System.out.println(Arrays.toString(
                s.solution(new int[][]{{1,2},{1,3}},new int[]{0,7,3})
        ));
    }

    static List<Integer> answer = new LinkedList<>();

    public int[] solution(int[][] edges, int[] target) {


        List<List<Integer>> tree = new ArrayList<>();

        for (int i = 0; i <= edges.length + 1; i++) {
            tree.add(new LinkedList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            tree.get(from).add(to);
        }

        for (List<Integer> list : tree) {
            list.sort(Integer::compareTo);
        }

        search(tree, target, new LinkedList<>());

        return answer.size() == 0 ? new int[]{-1} : answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private void search(List<List<Integer>> tree, int[] target, List<Integer> result) {

        boolean isEnd = true;

        for (int i : target) {
            if (i > 0) {
                isEnd = false;
                break;
            }
        }

        if (isEnd) {
            if (answer.size() == 0) {
                answer = result;
            } else if (answer.size() > result.size()){
                answer = result;
            }

            return;
        }


        for (int i = 1; i <= 3; i++) {

            List<Integer> root = tree.get(1);
            int next = 1;
            while (root.size() != 0) {
                next = root.remove(0);
                root.add(next);
                root = tree.get(next);
            }

            target[next - 1] -= i;
            if (target[next - 1] < 0) {
                target[next - 1] += i;
                continue;
            }

            result.add(i);
            search(tree, target, result);
            result.remove(result.lastIndexOf(i));

            target[next - 1] += i;
        }
    }
}
