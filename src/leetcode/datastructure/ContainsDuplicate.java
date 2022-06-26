package leetcode.datastructure;

import java.util.*;

// https://leetcode.com/problems/contains-duplicate/
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            int value = map.getOrDefault(num, 0);
            if (value > 0) return true;
            else map.put(num, value + 1);
        }

        return false;
    }
}
