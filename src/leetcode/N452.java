package leetcode;

import java.util.Arrays;
import java.util.Comparator;

// Minimum Number of Arrows to Burst Balloons
public class N452 {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(p -> p[0]));

        int answer = 1;
        int[] range = {points[0][0], points[0][1]};
        for (int[] point : points) {
            int start = point[0];
            int end = point[1];
            if (start <= range[1]) {
                range[0] = Math.max(range[0], start);
                range[1] = Math.min(range[1], end);
            } else {
                range = new int[]{start, end};
                answer += 1;
            }
        }

        return answer;
    }

}
