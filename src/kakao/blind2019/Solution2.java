package kakao.blind2019;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42890
// Brute Force
public class Solution2 {
    public static void main(String[] args) {
        Solution2 s = new Solution2();
        System.out.println(s.solution(
                new String[][]{
                        {"100", "ryan", "music", "2"},
                        {"200", "apeach", "math", "2"},
                        {"300", "tube", "computer", "3"},
                        {"400", "con", "computer", "4"},
                        {"500", "muzi", "music", "3"},
                        {"600", "apeach", "music", "2"}
                }));
    }

    public static List<List<Integer>> candidates;
    public static int answer;

    public int solution(String[][] relation) {
        answer = 0;

        candidates = new LinkedList<>();

        for (int i = 1; i <= relation[0].length; i++) {
            getKey(i, relation, new LinkedList<>(), 0);
        }

        return answer;
    }

    private void getKey(int count, String[][] relation, List<Integer> key, int next) {

        if (count == 0) {
            if (isCandidateKey(relation, key) && isAtom(key)) {
                candidates.add(key);
                answer++;
            }
            return;
        }

        for (int i = next; i < relation[0].length; i++) {
            key.add(i);
            getKey(count - 1, relation, key, i + 1);
            key.remove(Integer.valueOf(i));
        }
    }

    // 해당 키로 row를 식별할 수 있는지 확인
    public boolean isCandidateKey(String[][] relation, List<Integer> keys) {

        Map<String, Integer> map = new HashMap<>();

        for (String[] row : relation) {

            StringBuilder key = new StringBuilder();

            for (Integer k : keys) {
                key.append(row[k]);
            }

            if (map.containsKey(key.toString())) return false;
            map.put(key.toString(), 1);
        }

        return true;
    }

    public boolean isAtom(List<Integer> keys) {

        for (List<Integer> candidate : candidates) {
            int count = 0;
            for (Integer key : keys) {
                if (candidate.contains(key)) {
                    count += 1;
                }
            }
            if (count == candidate.size()) return false;
        }

        return true;
    }
}
