package oneThousandAndOneHundredToTwoHundred;

import org.junit.jupiter.api.Test;
import pojo.ListNode;
import utils.ParamUtils;
import utils.PrintUtils;

import java.util.*;

/**
 * @date : 2019/08/28 15:20:49
 * @author: liangenmao
 */
public class RemoveZeroSumSublists1171 {
    /**
     * 未考虑到[1,-1]
     */
    public ListNode result1(ListNode head) {
        Map<Integer, ListNode> sumMap = new HashMap<>();
        ListNode curr = head;
        int sum = 0;
        while (curr != null) {
            sum += curr.val;
            if (sumMap.containsKey(sum)) {
                ListNode remove = sumMap.get(sum).next;
                sumMap.get(sum).next = curr.next;
                curr = sumMap.get(sum);
                int value = sum + remove.val;
                while (value != sum) {
                    sumMap.remove(value);
                    remove = remove.next;
                    value += remove.val;
                }
            } else {
                sumMap.put(sum, curr);
            }
            curr = curr.next;
        }
        return head;
    }

    public ListNode result2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer, ListNode> prefixSumMap = new HashMap<>();
        ListNode p = dummy;
        int prefixSum = 0;
        while (p != null) {
            prefixSum += p.val;
            if (prefixSumMap.containsKey(prefixSum)) {
                p = prefixSumMap.get(prefixSum).next;
                int val = prefixSum + p.val;
                while (val != prefixSum) {
                    prefixSumMap.remove(val);
                    p = p.next;
                    val += p.val;
                }
                prefixSumMap.get(prefixSum).next = p.next;
            } else {
                prefixSumMap.put(prefixSum, p);
            }
            p = p.next;
        }
        return dummy.next;
    }

    @Test
    public void removeZeroSumSublists() {
        String listNode = "[1,2,3,-3,-2]";
        ListNode head = ParamUtils.getListNode(listNode);
        Object result = result1(head);
        PrintUtils.print(result);
    }
}
