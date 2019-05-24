package oneToHundred;

import org.junit.jupiter.api.Test;
import pojo.ListNode;

/**
 * @date : 2019/03/25 15:57
 * @author: liangenmao
 */
public class ReverseNodesinkGroup {

    public ListNode result1(ListNode head, int k) {
        ListNode curr = new ListNode(0);
        curr.next = head;
        ListNode resultHead = curr;
        while (true) {
            ListNode listNode = curr.next;
            for (int i = 0; i < k; i++) {
                if (listNode == null) {
                    return resultHead.next;
                }
                listNode = listNode.next;
            }
            //将要替换的列表边替换边提出来
            ListNode prev = null;
            ListNode last = null;
            for (int i = 0; i < k; i++) {
                ListNode now = curr.next;
                if (i == 0){
                    last = now;
                }
                curr.next = now.next;
                now.next = prev;
                prev = now;
            }
            last.next = curr.next;
            curr.next = prev;
            curr = last;
        }
    }

    public ListNode result2(ListNode head, int k) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;
        ListNode check = head;
        int canProceed = 0;
        int count = 0;
        // 检查链表长度是否满足翻转
        while (canProceed < k && check != null) {
            check = check.next;
            canProceed++;
        }
        // 满足条件，进行翻转
        if (canProceed == k) {
            while (count < k && cur != null) {
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
                count++;
            }
            if (next != null) {
                // head 为链表翻转后的尾节点
                head.next = result2(next, k);
            }
            // prev 为链表翻转后的头结点
            return prev;
        } else {
            // 不满住翻转条件，直接返回 head 即可
            return head;
        }
    }

    private ListNode[] swap(ListNode head, int k) {
        ListNode[] headTail = new ListNode[4];
        ListNode cur = head.next;
        ListNode tag;
        ListNode newHead = head;
        newHead.next = null;
        headTail[1] = head;
        headTail[0] = head;
        while (cur != null) {
            tag = cur;
            cur = cur.next;
            tag.next = newHead;
            newHead = tag;
            headTail[0] = newHead;
            if (--k == 1) {
                headTail[2] = cur;
                headTail[3] = headTail[1];
                break;
            }
        }
        return headTail;
    }

    @Test
    public void reverseKGroup() {
/*        ListNode listNode = new ListNode(0);
        ListNode head = listNode;
        Random random = new Random();
        for (int i = 0; i < 1000000000; i++) {
            ListNode l = new ListNode(random.nextInt(10));
            listNode.next = l;
            listNode = l;
        }
        listNode = head.next;*/
        ListNode listNode = newListNode(1, 2, 3, 4, 5,6,7,8,9);
        int k = 4;
        Object result = result2(listNode, k);
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
