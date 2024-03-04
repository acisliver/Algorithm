package leetcode;

import java.util.Arrays;

// Bag of Tokens
public class N948 {
    public int bagOfTokensScore(int[] tokens, int power) {
        if (tokens.length == 0) {
            return 0;
        }

        int score = 0;
        int max = 0;

        Arrays.sort(tokens);
        int small = 0;
        int big = tokens.length - 1;

        if (power < tokens[small]) {
            return 0;
        }

        while (small <= big) {
            if (power >= tokens[small]) {
                power -= tokens[small];
                score += 1;
                small += 1;
                max = Math.max(score, max);
            } else {
                power += tokens[big];
                score -= 1;
                big -= 1;
            }
        }

        return max;
    }
}
