package kakao.blind2019;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://school.programmers.co.kr/learn/courses/30/lessons/42889
// implementation
public class Solution1 {

    public static void main(String[] args) {
        Solution1 s = new Solution1();
        System.out.println(Arrays.toString(s.solution(5, new int[]{3, 3, 3, 3})));
    }

    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];

        int[] reachPeople = new int[N + 1];

        for (int stage : stages) {
            for (int i = 0; i < stage; i++) {
                reachPeople[i]++;
            }
        }

        PriorityQueue<FailRate> failRatePq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            if (reachPeople[i] == 0) {
                failRatePq.add(new FailRate(1, 0, i + 1));
            } else {
                failRatePq.add(new FailRate(reachPeople[i], reachPeople[i] - reachPeople[i + 1], i + 1));
            }
        }

        for (int i = 0; i < N; i++) {
            answer[i] = failRatePq.poll().num;
        }

        return answer;
    }

    public class FailRate implements Comparable<FailRate>{

        public double mother;
        public double child;
        public int num;

        public FailRate(int mother, int child, int num) {
            this.mother = mother;
            this.child = child;
            this.num = num;
        }

        @Override
        public int compareTo(FailRate o) {
            if ((o.child * this.mother) - (this.child * o.mother) == 0) {
                return this.num - o.num;
            } else {
                return (int) ((o.child * this.mother) - (this.child * o.mother));
            }
        }
    }
}
