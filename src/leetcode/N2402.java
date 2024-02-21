package leetcode;

import java.util.*;

// Meeting Rooms III
public class N2402 {

    public static void main(String[] args) {
        new N2402().mostBooked(2, new int[][]{{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}});
    }

    public int mostBooked(int n, int[][] meetings) {
        long[] rooms = new long[n];
        int[] freq = new int[n];
        Arrays.sort(meetings, Comparator.comparingInt((m) -> m[0]));

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            boolean isAllocated = false;
            int minIdx = -1;
            long minEndTime = Long.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (rooms[i] < minEndTime) {
                    minIdx = i;
                    minEndTime = rooms[i];
                }
                if (rooms[i] <= start) {
                    isAllocated = true;
                    freq[i] += 1;
                    rooms[i] = end;
                    break;
                }
            }
            if (!isAllocated) {
                freq[minIdx] += 1;
                rooms[minIdx] += (end - start);
            }
        }

        int max = 0;
        int maxRoom = -1;
        for (int j = 0; j < freq.length; j++) {
            int f = freq[j];
            if (f > max) {
                maxRoom = j;
                max = f;
            }
        }

        return maxRoom;
    }
}
