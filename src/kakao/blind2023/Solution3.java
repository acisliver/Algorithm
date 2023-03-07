package kakao.blind2023;

import java.util.Arrays;

// 이모티콘 할인 행사
// https://school.programmers.co.kr/learn/courses/30/lessons/150368
class Solution3 {

    public static void main(String[] args) {
        Solution3 s = new Solution3();
        System.out.println(Arrays.toString(s.solution(new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200},{32, 6900}}, new int[]{1300, 1500, 1600, 4900})));
    }

    private static final int[] RATE = {90, 80, 70, 60};
    private static int EMOTICON_PLUS = 0;
    private static int TOTAL_SALES = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        getPrices(emoticons, users, 0, new int[emoticons.length]);
        return new int[]{EMOTICON_PLUS, TOTAL_SALES};
    }

    private void getPrices(int[] emoticons, int[][] users, int cur, int[] rates) {
        if (cur == emoticons.length) {
            updateAnswer(emoticons, users, rates);
            return;
        }

        for (int rate : RATE) {
            rates[cur] = rate;
            getPrices(emoticons, users, cur + 1, rates);
        }
    }

    private void updateAnswer(int[] emoticons, int[][] users, int[] rates) {

        int ePlus = 0;
        int totalExpense = 0;

        for (int[] user : users) {
            int expense = 0;
            int rate = user[0];
            int price = user[1];
            for (int i = 0; i < rates.length; i++) {
                if (100 - rates[i] >= rate) {
                    expense += emoticons[i] * rates[i] / 100;
                }
                if (expense >= price) {
                    ePlus += 1;
                    expense = 0;
                    break;
                }
            }
            totalExpense += expense;
        }

        if (ePlus > EMOTICON_PLUS) {
            EMOTICON_PLUS = ePlus;
            TOTAL_SALES = totalExpense;
        } else if (ePlus == EMOTICON_PLUS) {
            TOTAL_SALES = Math.max(totalExpense, TOTAL_SALES);
        }
    }


}
