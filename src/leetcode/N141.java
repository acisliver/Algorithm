package leetcode;

// Linked List Cycle
public class N141 {
    public boolean hasCycle(ListNode list) {
        ListNode fast = list;
        ListNode slow = list;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.equals(slow)) {
                return true;
            }
        }

        return false;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
