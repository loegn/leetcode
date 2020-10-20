package daily;

import pojo.ListNode;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 143. 重排链表
 * https://leetcode-cn.com/problems/reorder-list/
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 */
public class No143 {
    public void reorderList1(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }

    public void reorderList3(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }
        int size = list.size();
        int a = 0;
        int b = size - 1;
        int max = size / 2;
        curr = new ListNode(0);
        curr.next = head;
        for (int i = 0; i < max; i++) {
            ListNode next = list.get(a);
            ListNode nextNext = list.get(b);
            curr.next = next;
            next.next = nextNext;
            curr = nextNext;
            a++;
            b--;
        }
        if (size > 1 && (size & 1) == 1) {
            list.get(++b).next = list.get(a);
        }
        list.get(a).next = null;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
//        a.next = b;
//        b.next = c;
//        c.next = d;
//        d.next = e;
        No143 no143 = new No143();
        no143.reorderList3(a);
        PrintUtils.print(a);
    }
}
