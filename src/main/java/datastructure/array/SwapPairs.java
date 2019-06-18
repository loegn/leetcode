package datastructure.array;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import pojo.ListNode;
import utils.ParamUtils;

/**
 * @date : 2019/06/14 16:42:36
 * @author: liangenmao
 */
public class SwapPairs {
    public ListNode result1(ListNode head) {
        ListNode result = new ListNode(0);
        result.next = head;
        swap(result);
        return result.next;
    }

    private void swap(ListNode listNode){
        ListNode next = listNode.next;
        if(next == null){
            return;
        }
        ListNode nextNext = next.next;
        if(nextNext == null){
            return;
        }
        next.next = nextNext.next;
        nextNext.next = next;
        listNode.next = nextNext;
        swap(next);
    }

    @Test
    public void swapPairs() {
        String listNode = "1->2->3->4";
        ListNode head = ParamUtils.getListNode(listNode);
        Object result = result1(head);
        PrintUtils.print(result);
    }
}
