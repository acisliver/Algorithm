package leetcode;

import java.util.*;

// https://leetcode.com/problems/find-all-anagrams-in-a-string/
// Sliding Window
public class N438 {
    public static void main(String[] args) {
        System.out.println(new N438().findAnagrams("abacbabc", "abc"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        // 잘못된 입력 리턴
        if (s.length() == 0 || p.length() == 0 || s.length() < p.length()) return new ArrayList<>();

        // Map에 Anagram 알파벳 개수 저장
        List<Integer> answer = new LinkedList<>();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {
            char key = p.charAt(i);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int start = 0;
        int end = 0;
        int len = p.length();
        int diff = len;         // 아나그램과 Window간 차이

        char temp;

        // 첫 번째 Window 만들기
        while (end < len) {
            temp = s.charAt(end);

            if (map.containsKey(temp)) {    // Anagram에 사용되는 알파벳의 경우
                map.put(temp, map.get(temp) - 1);

                if (map.get(temp) >= 0) {   // 음수가 되지 않았다 => 아나그램에 사용된다
                    diff--;
                }
            }

            end++;
        }

        if (diff == 0) {    // 차이가 없으므로 정답으로 추가
            answer.add(start);
        }

        // 만들어진 Window에서 앞에서 하나 제거, 뒤에서 하나 생성
        while (end < s.length()) {
            temp = s.charAt(start);

            if (map.containsKey(temp)) {
                if (map.get(temp) >= 0) {
                    diff++;
                }

                map.put(temp, map.get(temp) + 1);
            }

            start++;

            temp = s.charAt(end);

            if (map.containsKey(temp)) {
                map.put(temp, map.get(temp) - 1);

                if (map.get(temp) >= 0) {
                    diff--;
                }
            }

            end++;

            if (diff == 0) {
                answer.add(start);
            }
        }

        return answer;
    }
}
