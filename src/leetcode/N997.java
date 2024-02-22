package leetcode;

import java.util.*;

// Find the Town Judge
public class N997 {
    public int findJudge(int n, int[][] trust) {
        int[] count = new int[n];

        for (int[] t : trust) {
            int a = t[0] - 1;
            int b = t[1] - 1;
            count[a] -= 1;
            count[b] += 1;
        }

        for (int i = 0; i < n; i++) {
            if (count[i] == n - 1) {
                return i + 1;
            }
        }

        return -1;
    }
}
