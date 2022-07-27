package leetcode;

// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
// LinkedList, Two Pointer
public class N82 {
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
        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        ListNode cur = head;
        prev.next = cur;

        while(cur != null) {
            // 같은 숫자라면 같은 숫자의 끝부분까지 cur 이동
            while(cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }

            if (prev.next != cur) { // 중복 확인
                prev.next = cur.next;   // 중복된 값 다음 값을 prev의 다음 값으로 함
            } else {                // 중복 없음
                prev = prev.next;       // 다음 탐색으로 이동
            }
            cur = cur.next;
        }

        return dummy.next;
    }
}
