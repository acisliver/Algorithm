package leetcode;

import java.util.*;

// Find the Town Judge
public class N997 {
    public int findJudge(int n, int[][] trust) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new HashSet<>());
        }

        for (int[] tr : trust) {
            int a = tr[0];
            int b = tr[1];
            map.get(a).add(b);
        }

        System.out.println(map);

        // 1. town judge trusts nobody
        int candidate = -1;
        for (var entry : map.entrySet()) {
            if (entry.getValue().size() == 0) {
                if (candidate == -1) {
                    candidate = entry.getKey();
                } else {
                    return -1;
                }
            }
        }

        // 2. EveryBody trusts the town judge (except for the town judge)
        for (var entry : map.entrySet()) {
            if (entry.getKey() == candidate) {
                continue;
            }
            if (!entry.getValue().contains(candidate)) {
                return -1;
            }
        }

        return candidate;
    }
}
