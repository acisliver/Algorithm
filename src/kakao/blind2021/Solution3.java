package kakao.blind2021;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/72412
// HashMap, 백트래킹
public class Solution3 {

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(Arrays.toString(solution3.solution(new String[] {
                "java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"
        }, new String[] {
                        "java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"
                }
                )));
    }

    static HashMap<String, List<Integer>> group;

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        group = new HashMap<>();

        for (String s : info) {
            String[] infoArr = s.split(" ");
            for (int i = 0; i < 5; i++) {
                 makeKey(new String[]{infoArr[0], infoArr[1], infoArr[2], infoArr[3]}, Integer.parseInt(infoArr[4]), i, 0);
            }
        }

        for (int i = 0; i < query.length; i++) {
            String[] qArr = query[i].split(" ");
            int score = Integer.parseInt(qArr[qArr.length - 1]);
            qArr[qArr.length - 1] = "";
            String key = String.join(" ", qArr).trim();

            List<Integer> scores = group.get(key);
            scores.add(score);
            scores.sort(Integer::compareTo);

            int index = scores.size() - 1 - scores.indexOf(score);
            answer[i] = index;
            scores.remove(index);
        }

        return answer;
    }

    private void makeKey(String[] info, int score, int dontCare, int where) {
        if (dontCare == 0) {
            String key = String.join(" and ", info);

            List<Integer> scores;

            if (group.containsKey(key)) {
                scores = group.get(key);
            } else {
                scores = new LinkedList<>();
                group.put(key, scores);
            }

            scores.add(score);

            return;
        }

        for (int i = where; i < info.length; i++) {
            String temp = info[i];
            info[i] = "-";
            makeKey(info, score, dontCare - 1, i + 1);
            info[i] = temp;
        }
    }
}
