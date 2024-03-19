package leetcode;

import java.util.*;

// Task Scheduler
public class N621 {

    public static void main(String[] args) {
        new N621().leastInterval(new char[]{'A','A','A','B','B','B','C','D','E','F','G','H','I','J','K'}, 7);
    }

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        pq.addAll(map.entrySet());

        int answer = 0;
        int multi = 0;
        List<Map.Entry<Character, Integer>> temp = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            multi = pq.size();
            if (multi <= n) {   // idle
                for (int i = 0; i < multi; i++) {
                    var entry = pq.poll();
                    if (entry.getValue() > 1) {
                        temp.add(Map.entry(entry.getKey(), entry.getValue() - 1));
                    }
                    answer += 1;
                    sb.append(entry.getKey()).append(" ");
                }
                pq.addAll(temp);
                temp.clear();
                answer += n - multi + 1;
                sb.append("idle ".repeat(n - multi + 1));
            } else {
                for (int i = 0; i <= n; i++) {
                    var entry = pq.poll();
                    if (entry.getValue() > 1) {
                        temp.add(Map.entry(entry.getKey(), entry.getValue() - 1));
                    }
                    answer += 1;
                    sb.append(entry.getKey()).append(" ");
                }
                pq.addAll(temp);
                temp.clear();
            }

        }

        if (multi <= n) {
            answer -= n - multi + 1;
        }

        System.out.println(sb);
        System.out.println(answer);

        return answer;
    }


}
