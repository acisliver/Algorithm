package programers;

import java.util.*;

// 상담원 인원
// https://school.programmers.co.kr/learn/courses/30/lessons/214288?language=java
public class N214288 {
    static int minTime = Integer.MAX_VALUE;

    public int solution(int k, int n, int[][] reqs) {
        int[] mentoringType = new int[k];
        Arrays.fill(mentoringType, 1);
        chooseMentoringType(k, n - k, mentoringType, reqs);
        return minTime;
    }

    // 1. 멘토의 상담 유형 결정
    void chooseMentoringType(int k, int n, int[] mentoringType, int[][] reqs) {
        if (n == 0) {
            int waitingTime = getWaitingTime(mentoringType, reqs);
            minTime = Math.min(waitingTime, minTime);
            return;
        }

        for (int i = 0; i < k; i++) {
            mentoringType[i] += 1;
            chooseMentoringType(k, n - 1, mentoringType, reqs);
            mentoringType[i] += 1;
        }
    }

    int getWaitingTime(int[] type, int[][] reqs) {
        int waitingTime = 0;
        List<Queue<int[]>> waitings = new ArrayList<>();

        for (int t : type) {
            waitings.add(new LinkedList<>());
        }

        for (int[] req : reqs) {
            int reqType = req[2] - 1;
            waitings.get(reqType).add(req);
        }

        for (int i = 0; i < type.length; i++) {
            waitingTime += calcWaitingTime(type[i], waitings.get(i));
        }

        return waitingTime;
    }

    private int calcWaitingTime(int mentors, Queue<int[]> reqs) {
        int waitingTime = 0;
        int[] times = new int[mentors];

        while (!reqs.isEmpty()) {
            boolean isMentoring = false;
            int[] req = reqs.poll();
            int reqAt = req[0];
            int reqTime = req[1];

            for (int i = 0; i < times.length; i++) {
                int time = times[i];
                if (time <= reqAt) {
                    times[i] += reqAt + reqTime;
                    isMentoring = true;
                    break;
                }
            }

            if (isMentoring) continue;

            int minMentor = -1;
            int minTime = 123456;
            for (int i = 0; i < times.length; i++) {
                if (minTime > times[i]) {
                    minMentor = i;
                }
            }

            waitingTime += times[minMentor] - reqAt;
            times[minMentor] = reqAt + reqTime;
        }

        return waitingTime;
    }
}
