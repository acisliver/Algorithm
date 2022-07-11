package leetcode;

import java.util.HashMap;

// https://leetcode.com/problems/linked-list-cycle/
// LinkedList & Hash
public class HasCycle {
    public boolean hasCycle(ListNode list) {
        Map<ListNode, Integer> map = new HashMap<>();

        while(list != null) {
            list = list.next;

            if (map.containsKey(list)) {
                return true;
            }

            map.put(list, 0);
        }

        return false;
    }
}
