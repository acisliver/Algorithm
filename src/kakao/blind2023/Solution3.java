package kakao.blind2023;

import java.util.Arrays;

// 각 이모티콘 할인율에 따라 달라짐
class Solution3 {

    public static void main(String[] args) {
        Solution3 s = new Solution3();
        System.out.println(Arrays.toString(s.solution(new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200},{32, 6900}}, new int[]{1300, 1500, 1600, 4900})));
    }

    static int[] RATE = {90, 80, 70, 60};
    static int[] answer = {0, 0};

    public int[] solution(int[][] users, int[] emoticons) {

        // 할인율별 이모티콘 가격 책정
        getPrices(emoticons, users, 0, new int[emoticons.length]);

        return answer;
    }

    public void getPrices(int[] emoticons, int[][] users, int cur, int[] rate) {
        if (cur == emoticons.length) {
            int ePlus = 0;
            int sum = 0;

            for (int[] user : users) {
                int buyEmo = user[0];
                int buyPlus = user[1];
                int total = 0;

                for (int i = 0; i < rate.length; i++) {
                    if (rate[i] >= buyEmo) {
                        total += emoticons[i];
                    }

                    if (total >= buyPlus) {
                        ePlus++;
                        total = 0;
                        break;
                    }
                }

                sum += total;
            }

            if (ePlus > answer[0]) {
                answer[0] = ePlus;
                answer[1] = sum;
            } else if (ePlus == answer[0]) {
                answer[1] = Math.max(answer[1], sum);
            }
            return;
        }

        for (int i = 0; i < RATE.length; i++) {
            int temp = emoticons[cur];
            emoticons[cur] = emoticons[cur] / 100 * RATE[i];
            rate[cur] = 100 - RATE[i];
            getPrices(emoticons, users, cur + 1, rate);
            emoticons[cur] = temp;
        }
    }
}
