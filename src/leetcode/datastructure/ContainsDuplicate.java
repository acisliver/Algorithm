package leetcode.datastructure;

import java.util.*;

// https://leetcode.com/problems/contains-duplicate/
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) return true; // 이미 들어있는 요소의 경우 add()는 false를 리턴한다
        }
        return false;
    }
}
