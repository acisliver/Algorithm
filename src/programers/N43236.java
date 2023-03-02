package programers;

// 징검다리
// https://school.programmers.co.kr/learn/courses/30/lessons/43236?language=java
import java.util.*;

class N43236 {

    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);

        long lo = 0;
        long hi = distance + 1;
        while (lo + 1 < hi) {
            long mid = (lo + hi) >>> 1;

            if (check(mid, n, rocks)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return (int) lo;
    }

    private boolean check(long minDistance, int n, int[] rocks) {
        int from = 0;
        int removeCount = 0;

        for (int rock : rocks) {
            int distance = rock - from;
            if (distance < minDistance) {
                removeCount += 1;
            } else {
                from = rock;
            }
        }

        return removeCount <= n;
    }

}

