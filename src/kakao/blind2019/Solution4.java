package kakao.blind2019;

import java.util.LinkedList;
import java.util.List;

public class Solution4 {

    public static void main(String[] args) {
        Solution4 s = new Solution4();
        System.out.println(s.solution(new int[]{3, 1, 2}, 5));
    }

    public int solution(int[] food_times, long k) {
        int answer = 0;
        List<Food> list = new LinkedList<>();

        long len = food_times.length;

        long lap = k / len;
        long rest = k % len;

        for (int i = 0; i < food_times.length; i++) {
            food_times[i] -= lap;
            if (food_times[i] > 0) {
                list.add(new Food(i, food_times[i]));
            } else {
                rest -= food_times[i];
            }
        }

        int index = 0;
        while (rest + 1 > 0) {
            if (list.isEmpty()) return -1;

            Food curFood = list.get(index);
            answer = index;

            if (curFood.amount == 1) {
                list.remove(index);
                if (index != 0) {
                    index--;
                }
            } else {
                curFood.amount -= 1;
            }

            rest--;
            index++;
            if (index >= list.size()) {
                index -= list.size();
            }
        }

        return answer + 1;
    }

    class Food {
        int num;
        int amount;

        Food (int num, int amount) {
            this.num = num;
            this.amount = amount;
        }
    }
}

