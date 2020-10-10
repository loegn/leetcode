package daily;

import pojo.ListNode;

import java.util.HashSet;

public class No142 {
    public ListNode detectCycle1(ListNode head) {
        HashSet<ListNode> set = new HashSet();
        while (head != null) {
            set.add(head);
            head = head.next;
            if (set.contains(head)) {
                return head;
            }
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        do {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while (slow != fast);
        while (slow != head) {
            slow = slow.next;
            head = head.next;
        }
        return slow;
    }

    public ListNode detectCycle3(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
}
