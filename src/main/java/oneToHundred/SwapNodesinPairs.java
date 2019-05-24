package oneToHundred;

import org.junit.jupiter.api.Test;
import pojo.ListNode;

/**
 * @date : 2019/03/25 15:25
 * @author: liangenmao
 */
public class SwapNodesinPairs {

    public ListNode result1(ListNode head) {
        ListNode curr = new ListNode(0);
        curr.next = head;
        ListNode result = curr;
        while (curr.next != null && curr.next.next != null){
            ListNode left = curr.next;
            ListNode right = curr.next.next;
            left.next = right.next;
            right.next = left;
            curr.next = right;
            curr = left;
        }
        return result.next;
    }

    public ListNode result2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = result2(next.next);
        next.next = head;
        return next;
    }

    @Test
    public void swapPairs() {
        ListNode listNode = newListNode(1, 2, 3, 4);
        Object result = result1(listNode);
        System.out.println(result);
    }

    ListNode newListNode(int... ints) {
        ListNode curr = new ListNode(0);
        ListNode head = curr;
        for (int i = 0; i < ints.length; i++) {
            ListNode listNode = new ListNode(ints[i]);
            curr.next = listNode;
            curr = curr.next;
        }
        return head.next;
    }
}
