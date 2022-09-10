package kakao.intern2022;

import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;

        for (int i : queue1) {
            q1.offer(i);
            sum1 += i;
        }

        for (int i : queue2) {
            q2.offer(i);
            sum2 += i;
        }

        int size1 = q1.size();
        int size2 = q2.size();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (sum1 == sum2) return answer;

            if (sum1 > sum2) {
                size1--;
                int popped = q1.poll();
                q2.offer(popped);
                sum1 -= popped;
                sum2 += popped;
            } else {
                size2--;
                int popped = q2.poll();
                q1.offer(popped);
                sum2 -= popped;
                sum1 += popped;
            }

            if (size1 <= 0 && size2 <= 0) return -1;
            answer++;
        }

        return -1;
    }
}
