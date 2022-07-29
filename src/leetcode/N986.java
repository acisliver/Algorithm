package leetcode;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/interval-list-intersections/
// Two Pointers
public class N986 {
    public static void main(String[] args) {
        new N986().intervalIntersection(
                new int[][]{
                        {0, 2}, {5, 10}, {13, 23}, {24, 25}
                },
                new int[][]{
                        {1, 5}, {8, 12}, {15, 24}, {25, 26}
                }
        );
    }

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        List<int[]> answer = new LinkedList<>();

        int p1 = 0;
        int p2 = 0;

        while (p1 < firstList.length && p2 < secondList.length) {
            int[] first = firstList[p1];
            int[] second = secondList[p2];

            // 1. 곂치지 않는 경우
            if (first[1] < second[0]) {
                if (p1 < firstList.length) {
                    p1++;
                    continue;
                } else {
                    break;
                }
            } else if (first[0] > second[1]) {
                if (p2 < secondList.length) {
                    p2++;
                    continue;
                } else {
                    break;
                }
            }

            // 2. 곂치는 경우
            if (first[0] < second[0]) {
                if (second[1] > first[1]) {
                    answer.add(new int[]{second[0], first[1]});
                    p1++;
                } else {
                    answer.add(second);
                    p2++;
                }
            } else {
                if (second[1] < first[1]) {
                    answer.add(new int[]{first[0], second[1]});
                    p2++;
                } else {
                    answer.add(first);
                    p1++;
                }
            }
        }

        return answer.toArray(new int[0][]);
    }
}
