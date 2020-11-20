package daily;

import pojo.ListNode;
import utils.ParamUtils;
import utils.PrintUtils;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/insertion-sort-list/
 */
public class No147 {
    public ListNode insertionSortList1(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }

    public ListNode insertionSortList2(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }
        Collections.sort(list, Comparator.comparingInt(a -> a.val));
        for (int i = 0; i < list.size(); i++) {
            list.get(i).next = i == list.size() - 1 ? null : list.get(i + 1);
        }
        return list.isEmpty() ? null : list.get(0);
    }

    public static void main(String[] args) {
        No147 no147 = new No147();
        ListNode input = ParamUtils.getListNode("4->2->1->3");
        ListNode output = no147.insertionSortList1(input);
        PrintUtils.print(output);
    }
}
