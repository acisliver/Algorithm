package leetcode;

import java.util.*;

// Find Players With Zero or One Losses
public class N2225 {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> loseCount = new HashMap<>();

        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];

            loseCount.put(winner, loseCount.getOrDefault(winner, 0));
            loseCount.put(loser, loseCount.getOrDefault(loser, 0) + 1);
        }

        List<Integer> answer1 = new ArrayList<>();
        List<Integer> answer2 = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : loseCount.entrySet()) {
            if (entry.getValue() == 0) {
                answer1.add(entry.getKey());
            }
            if (entry.getValue() == 1) {
                answer2.add(entry.getKey());
            }
        }

        Collections.sort(answer1);
        Collections.sort(answer2);
        return List.of(answer1, answer2);
    }
}
