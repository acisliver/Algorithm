package kakao.intern2022;

import java.util.HashMap;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/118666
// HashMap
public class Solution1 {
    public static void main(String[] args) {
        Solution1 s = new Solution1();
        System.out.println(s.solution(new String[]{"AN", "CF", "MJ", "RT", "NA"}, new int[]{5, 3, 2, 7, 5}));
    }

    public String solution(String[] survey, int[] choices) {
        StringBuilder answer = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        Character[] type = new Character[] {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};

        for (Character character : type) {
            map.put(character, 0);
        }

        for (int i = 0; i < survey.length; i++) {
            char c1 = survey[i].charAt(0);
            char c2 = survey[i].charAt(1);
            int choice = choices[i];

            if (choice > 4) {
                map.put(c2, map.getOrDefault(c2, 0) + choice - 4);
            } else if (choice < 4) {
                map.put(c1, map.getOrDefault(c1, 0) + 4 - choice);
            }
        }

        for (int i = 0; i < type.length; i+=2) {
            char c1 = type[i];
            char c2 = type[i + 1];

            answer.append(map.get(c1) >= map.get(c2) ? c1 : c2);
        }

        return answer.toString();
    }
}
