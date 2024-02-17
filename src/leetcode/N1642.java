package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

// Furthest Building You Can Reach
public class N1642 {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];
            if (diff <= 0) {
                continue;
            }

            bricks -= diff;
            priorityQueue.add(diff);

            if (bricks < 0) {
                bricks += priorityQueue.poll();
                ladders -= 1;
            }

            if (ladders < 0) {
                return i;
            }
        }

        return heights.length - 1;
    }
}
