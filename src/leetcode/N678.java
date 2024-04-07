package leetcode;

import java.util.Arrays;

// Valid Parenthesis String
public class N678 {
    public boolean checkValidString(String s) {
        int[][] memo = new int[s.length()][s.length()];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return isValidString(0, 0, s, memo);
    }

    private boolean isValidString(int i, int openCount, String s, int[][] memo) {
        if (i == s.length()) {
            return openCount == 0;
        }

        if (memo[i][openCount] != -1) {
            return memo[i][openCount] == 1;
        }

        boolean isValid = false;

        if (s.charAt(i) == '*') {
            isValid |= isValidString(i + 1, openCount + 1, s, memo);
            if (openCount > 0) {
                isValid |= isValidString(i + 1, openCount - 1, s, memo);
            }
            isValid |= isValidString(i + 1, openCount, s, memo);
        } else {
            if (s.charAt(i) == '(') {
                isValid |= isValidString(i + 1, openCount + 1, s, memo);
            } else if (openCount > 0) {
                isValid |= isValidString(i + 1, openCount - 1, s, memo);
            }
        }

        memo[i][openCount] = isValid ? 1 : 0;
        return isValid;
    }
}
