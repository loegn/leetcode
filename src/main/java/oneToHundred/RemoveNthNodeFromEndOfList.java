package oneToHundred;

import org.junit.jupiter.api.Test;
import pojo.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @date : 2019/03/13 11:40
 * @author: liangenmao
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode result1(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode listNode = head;
        do {
            list.add(listNode);
            listNode = listNode.next;
        } while (listNode != null);
        if (n < 1) {
            return null;
        }
        if (n == 1) {
            if (list.size() == 1) {
                return null;
            }
            list.get(list.size() - n - 1).next = null;
            return head;
        }
        if (n == list.size()) {
            if (list.size() == 1) {
                return null;
            }
            return list.get(1);
        }
        if (n > list.size()) {
            return null;
        }
        list.get(list.size() - n - 1).next = list.get(list.size() - n + 1);
        return head;
    }

    public ListNode result2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public ListNode result3(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length  = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }

    @Test
    public void removeNthFromEnd() {
        ListNode listNode = new ListNode(0);
        result2(listNode,1);
    }
}
