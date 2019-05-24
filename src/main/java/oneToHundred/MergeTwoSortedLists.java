package oneToHundred;

import pojo.ListNode;

/**
 * @date : 2019/03/25 09:43
 * @author: liangenmao
 */
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);
        ListNode result = listNode;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                ListNode listNode1 = new ListNode(l2.val);
                listNode.next = listNode1;
                listNode = listNode1;
                l2 = l2.next;
            } else {
                ListNode listNode1 = new ListNode(l1.val);
                listNode.next = listNode1;
                listNode = listNode1;
                l1 = l1.next;
            }
        }
        if (l1 == null){
            listNode.next = l2;
        }else {
            listNode.next = l1;
        }
        return result.next;
    }
}
