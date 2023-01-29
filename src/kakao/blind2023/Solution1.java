package kakao.blind2023;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution1 {

    public static void main(String[] args) {
        Solution1 s = new Solution1();
        System.out.println(Arrays.toString(s.solution(
                "2020.01.01", new String[]{"Z 3", "D 5"}, new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"})));
    }

    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new LinkedList<>();

        HashMap<String, Integer> termMap = new HashMap<>();
        List<Date> dateList = new LinkedList<>();
        String[] todayArr = today.split("\\.");


        for (String term : terms) {
            String[] t = term.split(" ");
            termMap.put(t[0], Integer.valueOf(t[1]));
        }

        for (String privacy : privacies) {
            String[] a = privacy.split(" ");
            String[] date = a[0].split("\\.");
            String type = a[1];

            Date cur = new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
            cur.addMonth(termMap.get(type));
            dateList.add(cur);
        }

        for (int i = 0; i < dateList.size(); i++) {
            if (isFire(todayArr, dateList.get(i))) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public boolean isFire(String[] todayArr, Date date) {

        int tYear = Integer.parseInt(todayArr[0]);
        int tMonth = Integer.parseInt(todayArr[1]);
        int tDay = Integer.parseInt(todayArr[2]);

        if (tYear < date.year) {
            return false;
        } else if (tYear == date.year) {
            if (tMonth < date.month) {
                return false;
            } else if (tMonth == date.month) {
                if (tDay < date.day) {
                    return false;
                }
            }
        }

        return true;
    }

    public class Date {
        public int year;
        public int month;
        public int day;

        public Date(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public void addMonth(int month) {
            this.month += month;

            while (this.month > 12) {
                this.month -= 12;
                this.year++;
            }
        }
    }
}
