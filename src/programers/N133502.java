package programers;

import java.util.Arrays;
import java.util.Stack;

// 햄버거 만들기
// https://school.programmers.co.kr/learn/courses/30/lessons/133502
public class N133502 {

    public static void main(String[] args) {
        N133502 s = new N133502();
        System.out.println(s.solution(new int[]{2, 1, 1, 2, 3, 1, 2, 3, 1}));
    }

    public int solution(int[] ingredient) {
        int answer = 0;

        Stack<Integer> bugger = new Stack<>();

        for (int i : ingredient) {
            bugger.push(i);

            if (bugger.size() >= 4) {
                if (isBugger(bugger)) {
                    answer += 1;
                    makeBugger(bugger);
                }
            }
        }

        return answer;
    }

    private void makeBugger(Stack<Integer> bugger) {
        bugger.pop();
        bugger.pop();
        bugger.pop();
        bugger.pop();
    }

    private boolean isBugger(Stack<Integer> bugger) {
        int topBread = bugger.get(bugger.size() - 1);
        int meat = bugger.get(bugger.size() - 2);
        int vegetable = bugger.get(bugger.size() - 3);
        int bottomBread = bugger.get(bugger.size() - 4);

        return isBread(topBread) && isBread(bottomBread) && isMeat(meat) && isVegetable(vegetable);
    }

    private boolean isBread(int bread) {
        return bread == Ingredient.BREAD.number;
    }

    private boolean isVegetable(int vegetable) {
        return vegetable == Ingredient.VEGETABLE.number;
    }

    private boolean isMeat(int meat) {
        return meat == Ingredient.MEAT.number;
    }

    enum Ingredient {
        BREAD(1),
        VEGETABLE(2),
        MEAT(3),
        ;

        final int number;

        Ingredient(int number) {
            this.number = number;
        }
    }
}
