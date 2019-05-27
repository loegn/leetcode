package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import pojo.ListNode;
import utils.ParamUtils;

/**
 * @date : 2019/05/24 10:28:37
 * @author: liangenmao
 */
public class PartitionList86 {
    public ListNode result1(ListNode head, int x) {
        ListNode result = new ListNode(0);
        result.next = head;
        ListNode pre = result;
        ListNode left = null;
        ListNode leftPre = null;
        ListNode curr = result.next;
        while (curr != null) {
            if (curr.val < x) {
                if (left != null) {
                    pre.next = curr.next;
                    leftPre.next = curr;
                    curr.next = left;
                    leftPre = curr;
                    curr = pre.next;
                    continue;
                }
            } else {
                if (left == null) {
                    left = curr;
                    leftPre = pre;
                }
            }
            pre = curr;
            curr = curr.next;
        }
        return result.next;
    }

    @Test
    public void partition() {
        String listNode = "1->4->3->2->5->2";
        ListNode head = ParamUtils.getListNode(listNode);
        int x = 3;
        Object result = result1(head, x);
        PrintUtils.print(result);
    }
}
