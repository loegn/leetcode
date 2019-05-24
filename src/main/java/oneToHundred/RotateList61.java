package oneToHundred;

import org.junit.jupiter.api.Test;
import pojo.ListNode;
import utils.PrintUtils;

/**
 * @date : 2019/04/25 16:17
 * @author: liangenmao
 */
public class RotateList61 {
    /**
     * O(2n-k),O(1)
     */
    public ListNode result1(ListNode head, int k) {
        int length = 0;
        ListNode result = new ListNode(0);
        result.next = head;
        ListNode curr = result;
        //找出总长度和最后一个节点
        while (true) {
            if (curr.next != null) {
                length++;
            } else {
                break;
            }
            curr = curr.next;
        }
        //如果长度为0，直接返回
        if (length == 0) {
            return null;
        }
        k = k % length;
        //不需要判断，结果一样
//        if (k == 0) {
//            return head;
//        }
        //找出位移后列表的最后一个节点
        ListNode end = result;
        for (int i = 0; i < length - k; i++) {
            end = end.next;
        }
        //curr为原列表最后一个节点，位移后指向原第一个节点
        curr.next = result.next;
        result.next = end.next;
        end.next = null;
        return result.next;
    }

    @Test
    public void rotateRight() {
        ListNode n = new ListNode(1);
        n.next = new ListNode(2);
        int k = 4;
        Object result = result1(n, k);
        PrintUtils.print(result);
    }
}
