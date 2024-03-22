package leetcode;

// Palindrome Linked List
public class N234 {

    public boolean isPalindrome(ListNode head) {
        ListNode left = head;
        ListNode right = reversedHead(head);

        while (left != null && right != null && left != right) {
            if (left.val != right.val) {
                return false;
            }
            System.out.println(left.val + " " + right.val);
            left = left.next;
            right = right.next;
        }

        return true;
    }

    private ListNode reversedHead(ListNode head) {
        int size = size(head);
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;

        for (int i = 0; i < size / 2; i++) {
            cur = cur.next;
        }

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    private int size(ListNode head) {
        int size = 1;
        while (head != null) {
            head = head.next;
            size += 1;
        }

        return size;
    }

    private static class ListNode {
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
