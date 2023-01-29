package kakao.intern2022;

import java.util.LinkedList;

//
// LinkedList
public class Solution5 {

    static LinkedList<Integer> left = new LinkedList<>();
    static LinkedList<Integer> right = new LinkedList<>();
    static LinkedList<LinkedList<Integer>> rows = new LinkedList<>();

    public int[][] solution(int[][] rc, String[] operations) {
        int[][] answer = new int[rc.length][rc[0].length];

        for (int i = 0; i < rc.length; i++) {
            rows.add(new LinkedList<>());
        }

        for (int[] ints : rc) {
            left.add(ints[0]);
            right.add(ints[rc[0].length - 1]);
        }

        for (int i = 0; i < rc.length; i++) {
            for (int j = 1; j < rc[0].length - 1; j++) {
                rows.get(i).add(rc[i][j]);
            }
        }

        for (String operation : operations) {
            if (operation.equals("ShiftRow")) shiftRow();
            else rotate();
        }

        for (int i = 0; i < rc.length; i++) {
            answer[i][0] = left.poll();
            answer[i][rc[0].length - 1] = right.poll();
        }

        for (int i = 0; i < rc.length; i++) {
            for (int j = 1; j < rc[0].length - 1; j++) {
                answer[i][j] = rows.get(i).poll();
            }
        }

        return answer;
    }

    public void shiftRow () {
        left.addFirst(left.removeLast());
        right.addFirst(right.removeLast());
        rows.addFirst(rows.removeLast());
    }

    public void rotate() {
        // 위왼
        rows.get(0).addFirst(left.removeFirst());

        // 위오
        right.addFirst(rows.get(0).removeLast());

        // 아래오
        rows.get(rows.size() - 1).add(right.removeLast());

        // 아래왼
        left.add(rows.get(rows.size() - 1).removeFirst());
    }
}
