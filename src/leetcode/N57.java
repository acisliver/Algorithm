package leetcode;

import java.util.ArrayList;
import java.util.List;

// Insert Interval
public class N57 {

    public static void main(String[] args) {
        new N57().insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5});
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> answer = new ArrayList<>();
        int[] inserted = {-1, -1};
        int i = 0;

        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            answer.add(intervals[i]);
            i += 1;
        }

        // 중첩 발생
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i += 1;
        }
        answer.add(newInterval);

        while (i < intervals.length) {
            answer.add(intervals[i]);
            i += 1;
        }

        return answer.toArray(int[][]::new);
    }
}
