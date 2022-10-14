package kakao.blind2022;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/92334
// HashMap
public class Solution1 {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, Integer> idMap = new HashMap<>();
        Map<String, Set<String>> reportMap = new HashMap<>();

        for (String id : id_list) {
            idMap.put(id, 0);
            reportMap.put(id, new HashSet<>());
        }

        for (String s : report) {
            String[] r = s.split(" ");
            String from = r[0];
            String to = r[1];

            reportMap.get(from).add(to);
        }

        for (Set<String> set : reportMap.values()) {
            for (String s : set) {
                idMap.put(s, idMap.get(s) + 1);
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];

            Set<String> reportWho = reportMap.get(id);
            for (String who : reportWho) {
                if (idMap.get(who) >= k) {
                    answer[i]++;
                }
            }
        }

        return answer;
    }
}
