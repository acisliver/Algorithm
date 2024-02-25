package leetcode;

import java.util.*;

// Greatest Common Divisor Traversal
public class N2709 {

    public static void main(String[] args) {
        System.out.println(new N2709().canTraverseAllPairs(new int[]{4, 3, 12, 8}));
    }


    public boolean canTraverseAllPairs(int[] nums) {
        int max = 100000;
        Set<Integer> has = new HashSet<>();
        for (int num : nums) {
            has.add(num);
        }

        // edge case
        if (nums.length == 1) {
            return true;
        }
        if (has.contains(1)) {
            return false;
        }

        int[] sieve = new int[max + 1];
        for (int i = 2; i <= max; i++) {
            if (sieve[i] == 0) {
                for (int j = i; j <= max; j += i) {
                    sieve[j] = i;
                }
            }
        }

        int[] parents = new int[max + 1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        for (int num : nums) {
            int val = num;
            while (val > 1) {
                int prime = sieve[val];
                union(prime, num, parents);
                while (val % prime == 0) {
                    val /= prime;
                }
            }
        }

        int root = find(nums[0], parents);
        for (int i = 2; i <= max; i++) {
            if (has.contains(i)) {
                if (root != find(i, parents)) {
                    return false;
                }
            }
        }

        return true;
    }

    private void union(int a, int b, int[] parents) {
        int r1 = find(a, parents);
        int r2 = find(b, parents);

        if (r1 != r2) {
            if (r1 > r2) {
                parents[r1] = r2;
            } else {
                parents[r2] = r1;
            }
        }
    }

    private int find(int a, int[] parents) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a], parents);
    }

}
