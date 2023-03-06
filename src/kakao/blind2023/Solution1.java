package kakao.blind2023;

// 개인정보 수집 유효기간
// https://school.programmers.co.kr/learn/courses/30/lessons/150370?language=java
import java.util.*;

public class Solution1 {
    
    private static final int YEAR = 12;
    private static final int DAY = 28;

    public static void main(String[] args) {
        Solution1 s = new Solution1();
        System.out.println(Arrays.toString(s.solution(
                "2020.01.01", new String[]{"Z 3", "D 5"}, new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"})));
    }

    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new LinkedList<>();

        Map<String, Integer> termMap = new HashMap<>();

        for (String term : terms) {
            String[] t = term.split(" ");
            termMap.put(t[0], Integer.valueOf(t[1]));
        }

        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];
            if (isFire(privacy, termMap, today)) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean isFire(String privacy, Map<String, Integer> term, String today) {
        String[] split = privacy.split(" ");
        String date = split[0];
        String type = split[1];
        int days = getDays(date);
        int fireDays = getFireDays(days, type, term);

        return fireDays <= getDays(today);
    }

    private int getDays(String date) {
        String[] a = date.split("\\.");
        int year = Integer.parseInt(a[0]);
        int month = Integer.parseInt(a[1]);
        int day = Integer.parseInt(a[2]);

        return (year * YEAR * DAY) + (month * DAY) + day;
    }

    private int getFireDays(int days, String type, Map<String, Integer> term) {
        int expirationDays = term.get(type) * DAY;
        return days + expirationDays;
    }

}
