package leetcode;

// https://leetcode.com/problems/merge-two-sorted-lists/
// LinkedList
public class MergeTwoSortedLists {
    class ListNode {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode list;
        if (list1.val > list2.val) {
            list = list2;
            list2 = list2.next;
        } else {
            list = list1;
            list1 = list1.next;
        }
        ListNode answer = list;

        while(list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                list.next = list2;
                list2 = list2.next;
            } else {
                list.next = list1;
                list1 = list1.next;
            }
            list = list.next;
        }

        while(list1 != null) {
            list.next = list1;
            list1 = list1.next;
            list = list.next;
        }

        while(list2 != null) {
            list.next = list2;
            list2 = list2.next;
            list = list.next;
        }

        return answer;
    }
}
