package leetcode;

import java.util.*;

// Meeting Rooms III
public class N2402 {

    public static void main(String[] args) {
        new N2402().mostBooked(2, new int[][]{{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}});
    }

    public int mostBooked(int n, int[][] meetings) {
        int time = 0;
        int[] rooms = new int[n];
        int[] freq = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(m -> m[0]));
        Queue<int[]> queue = new LinkedList<>();

        pq.addAll(Arrays.asList(meetings));

        while (!pq.isEmpty()) {
            System.out.printf("time: %d\n", time);
            // 회의 대기열 등록
            while (!pq.isEmpty()) {
                int[] meeting = pq.peek();
                if (meeting[0] <= time) {
                    queue.add(pq.poll());
                } else {
                    break;
                }
            }

            // 현재 시각에 미팅이 없다면 가장 빨리 회의를 시작하는 시간으로 이동
            if (queue.isEmpty()) {
                time = pq.peek()[0];
                continue;
            }

            // 대기중인 회의 빈 방에 넣기
            int[] meeting = queue.peek();
            boolean isAllocated = false;
            int endTime = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (rooms[j] <= time) {
                    queue.poll();
                    rooms[j] = time + meeting[1] - meeting[0];
                    freq[j] += 1;
                    isAllocated = true;
                    System.out.printf("room: %d, start: %d, end: %d\n", j, time, rooms[j]);
                    if (queue.isEmpty()) {
                        break;
                    }
                    meeting = queue.peek();
                }
                endTime = Math.min(endTime, rooms[j]);
            }

            if (!isAllocated) {
                time = endTime;
            } else {
                time += 1;
            }

        }

        while (!queue.isEmpty()) {
            int[] meeting = queue.peek();
            boolean isAllocated = false;
            int endTime = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (rooms[j] <= time) {
                    queue.poll();
                    rooms[j] = time + meeting[1] - meeting[0];
                    freq[j] += 1;
                    isAllocated = true;
                    System.out.printf("room: %d, start: %d, end: %d\n", j, time, rooms[j]);
                    if (queue.isEmpty()) {
                        break;
                    }
                    meeting = queue.peek();
                }
                endTime = Math.min(endTime, rooms[j]);
            }

            if (!isAllocated) {
                time = endTime;
            } else {
                time += 1;
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
