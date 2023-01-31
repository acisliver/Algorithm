package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 컵라면
// https://www.acmicpc.net/problem/1781
/*
    1. 기한, 라면 순으로 내림차순 정렬
    2. 마지막 날짜부터 뒤로 계산
    3. 오늘과 데드라인이 같은 문제 문제 PQ에 담기
    4. PQ에 담긴 문제가 있다면 라면 획득
    5. 오늘과 데드라인이 같은 문제가 없으면 이전에 넣었던 문제를 풀어 라면 획득
 */
public class N1781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Problem> problems = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        int maxDay = 0;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            maxDay = Math.max(maxDay, input[0]);
            Problem problem = new Problem(input[0], input[1]);
            problems.offer(problem);
        }

//        while (!problems.isEmpty()) {
//            Problem problem = problems.poll();
//            System.out.printf("%d, %d\n", problem.deadline, problem.reward);
//        }

        PriorityQueue<Integer> rewards = new PriorityQueue<>(Comparator.reverseOrder());

        while (maxDay > 0) {
            while (!problems.isEmpty() && problems.peek().deadline == maxDay) {
                rewards.add(problems.poll().reward);
            }

            if (!rewards.isEmpty()) {
                answer += rewards.poll();
            }

            maxDay -= 1;
        }

        System.out.println(answer);
    }

    static class Problem implements Comparable<Problem> {
        int deadline;
        int reward;

        public Problem(int deadline, int reward) {
            this.deadline = deadline;
            this.reward = reward;
        }

        @Override
        public int compareTo(Problem o) {
            int descDeadline = Integer.compare(o.deadline, this.deadline);
            int descReward = Integer.compare(o.reward, this.reward);

            if (descDeadline == 0) {
                return descReward;
            }

            return descDeadline;
        }
    }
}
