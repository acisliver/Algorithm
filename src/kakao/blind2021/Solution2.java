package kakao.blind2021;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/72411
// HashMap, BackTracking
public class Solution2 {

    public static void main(String[] args) {

        Solution2 s = new Solution2();
        System.out.println(Arrays.toString(s.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4})));
    }

    static HashMap<String, Integer> map;

    public void getCombination(String[] order, int n, int where, List<String> result) {
        if (n == 0) {
            String course = String.join("", result);
            map.put(course, map.getOrDefault(course, 0) + 1);
            return;
        }

        for (int i = where; i < order.length; i++) {
            String menu = order[i];
            result.add(menu);
            getCombination(order, n - 1, i + 1, result);
            result.remove(menu);
        }
    }

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new LinkedList<>();

        for (int n : course) {
            map = new HashMap<>();
            int max = 0;

            for (String order : orders) {
                String[] orderArr = Arrays.stream(order.split(""))
                                .sorted().toArray(String[]::new);
                getCombination(orderArr, n, 0, new LinkedList<>());
            }

            for (Integer value : map.values()) {
                if (value < 2) continue;
                max = Math.max(max, value);
            }

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == max) answer.add(entry.getKey());
            }
        }

        answer.sort(String::compareTo);

        return answer.toArray(new String[0]);
    }
}
