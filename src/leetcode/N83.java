package leetcode;

// https://leetcode.com/problems/remove-duplicates-from-sorted-list/
// LinkedList, TwoPointer
public class N83 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;

        while(p2 != null) {
            p2 = p2.next;

            while(p2 != null && p1.val == p2.val) {
                p1.next = p2.next;
                p2 = p2.next;
            }

            p1 = p1.next;
        }

        return head;
    }
}
