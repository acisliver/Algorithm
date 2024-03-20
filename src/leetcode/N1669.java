package leetcode;

// Merge In Between Linked Lists
public class N1669 {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode answer = list1;
        ListNode aNode = null;
        ListNode bNode = null;
        int idx = 0;
        while (list1.next != null) {
            if (idx == a - 1) {
                aNode = list1;
            }
            if (idx == b + 1) {
                bNode = list1;
            }
            idx += 1;
            list1 = list1.next;
        }
        if (idx == b + 1) {
            bNode = list1;
        }
        ListNode first = list2;
        while (list2.next != null) {
            list2 = list2.next;
        }
        ListNode last = list2;

        aNode.next = first;
        last.next = bNode;

        return answer;
    }

    static class ListNode {
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
}
