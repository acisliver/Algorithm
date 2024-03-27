package kakao.intern2024;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/258711
public class Solution2 {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, List<Integer>> reverse = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (graph.containsKey(from)) {
                graph.get(from).add(to);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(to);
                graph.put(from, list);
            }
            if (reverse.containsKey(to)) {
                reverse.get(to).add(from);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(from);
                reverse.put(to, list);
            }
        }

        int candidate = 2;
        for (var entry : graph.entrySet()) {
            int key = entry.getKey();
            List<Integer> list = entry.getValue();
            if (list.size() >= 2 && !reverse.containsKey(key)) {
                candidate = key;
                // System.out.println(candidate);
                // System.out.println(graph.get(candidate));
            }
        }
        answer[0] = candidate;

        for (int start : graph.get(candidate)) {
            int e = 0;
            int n = 1;
            Set<Integer> visited = new HashSet<>();
            visited.add(start);
            Map<Integer, Set<Integer>> es = new HashMap<>();
            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (!graph.containsKey(cur)) {
                    break;
                }
                for (int next : graph.get(cur)) {
                    if (es.containsKey(cur)) {
                        if (es.get(cur).contains(next)) {
                            continue;
                        }
                    } else {
                        Set<Integer> nexts = new HashSet<>();
                        nexts.add(next);
                        es.put(cur, nexts);
                    }
                    if (!visited.contains(next)) {
                        n += 1;
                        visited.add(next);
                    }
                    q.add(next);
                    e += 1;
                }
            }

            if (e == n) {
                answer[1] += 1;
            } else if (e + 1 == n) {
                answer[2] += 1;
            } else {
                answer[3] += 1;
            }
        }

        return answer;
    }
}
