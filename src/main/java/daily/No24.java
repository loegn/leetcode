package daily;

import pojo.ListNode;

public class No24 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    public ListNode swapPairs2(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }

    public ListNode swapPairs3(ListNode head) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode curr = dummyNode;
        while (curr.next != null && curr.next.next != null) {
            ListNode next = curr.next;
            curr.next = curr.next.next;
            next.next = next.next.next;
            curr.next.next = next;
            curr = next;
        }
        return dummyNode.next;
    }
}
