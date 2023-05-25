package programers;

import java.util.*;


// 과제 진행하기
// https://school.programmers.co.kr/learn/courses/30/lessons/176962
public class N176962 {
    public static void main(String[] args) {
        N176962 n = new N176962();
        System.out.println(Arrays.toString(n.solution(new String[][]{{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}})));
    }
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        PriorityQueue<Plan> queue = new PriorityQueue<>();
        Queue<Plan> workingPlans = new LinkedList<>();
        Stack<Plan> pendingPlans = new Stack<>();

        for (String[] inputPlan : plans) {
            Plan plan = new Plan(inputPlan[0], getTime(inputPlan[1]), Integer.parseInt(inputPlan[2]));
            queue.add(plan);
        }

        Plan firstPlan = queue.poll();
        workingPlans.add(firstPlan);
        int startWorkTime = firstPlan.startTime;

        System.out.printf("start: %s\n", firstPlan.name);

        while (!queue.isEmpty()) {

            Plan nextPlan = queue.poll();
            Plan workingPlan = workingPlans.poll();
            int endWorkTime = startWorkTime + workingPlan.duration;
            int nextStartTime = nextPlan.startTime;

            if (endWorkTime > nextStartTime) {
                workingPlan.duration -= nextStartTime - startWorkTime;
                pendingPlans.push(workingPlan);
                System.out.printf("pause: %s rest: %d\n", workingPlan.name, workingPlan.duration);
            } else {
                answer.add(workingPlan.name);
                System.out.printf("done: %s\n", workingPlan.name);

                // 스택이 비지 않았으면 중간에 스택의 Plan도 실행했는지 확인
                int term = nextStartTime - endWorkTime;

                while (!pendingPlans.isEmpty() && term > 0) {
                    Plan pendingPlan = pendingPlans.pop();

                    System.out.printf("start: %s ", pendingPlan.name);
                    if (term >= pendingPlan.duration) {
                        term -= pendingPlan.duration;
                        answer.add(pendingPlan.name);
                        System.out.printf("done: %s\n", pendingPlan.name);
                    } else {
                        pendingPlan.duration -= term;
                        term = 0;
                        pendingPlans.push(pendingPlan);
                        System.out.printf("pause: %s rest: %d\n", pendingPlan.name, pendingPlan.duration);
                    }
                }
            }

            workingPlans.add(nextPlan);
            startWorkTime = nextPlan.startTime;
            System.out.printf("start: %s\n", nextPlan.name);
        }

        for (Plan workingPlan : workingPlans) {
            answer.add(workingPlan.name);
            System.out.printf("done: %s\n", workingPlan.name);
        }

        while (!pendingPlans.isEmpty()) {
            Plan pendingPlan = pendingPlans.pop();
            answer.add(pendingPlan.name);
            System.out.printf("done: %s\n", pendingPlan.name);
        }

        return answer.toArray(String[]::new);
    }

    int getTime(String time) {
        String[] timeSplit = time.split(":");
        int hour = Integer.parseInt(timeSplit[0]);
        int min = Integer.parseInt(timeSplit[1]);

        return hour * 60 + min;
    }

    static class Plan implements Comparable<Plan> {
        String name;
        int startTime;
        int duration;

        public Plan(String name, int startTime, int duration) {
            this.name = name;
            this.startTime = startTime;
            this.duration = duration;
        }

        @Override
        public int compareTo(Plan o) {
            return Integer.compare(this.startTime, o.startTime);
        }
    }
}
