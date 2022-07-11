package leetcode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/linked-list-cycle/
// LinkedList & Hash
public class HasCycle {
    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

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
