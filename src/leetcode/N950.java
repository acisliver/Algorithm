package leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// Reveal Cards In Increasing Order
public class N950 {
    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);

        int[] answer = new int[deck.length];
        Deque<Integer> indices = new LinkedList<>();

        for (int i = 0; i < answer.length; i++) {
            indices.add(i);
        }

        for (int card : deck) {
            int idx = indices.pop();
            answer[idx] = card;
            if (!indices.isEmpty()) {
                indices.add(indices.pop());
            }
        }

        return answer;
    }
}
