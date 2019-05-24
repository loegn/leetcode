package oneToHundred;

import org.junit.jupiter.api.Test;
import pojo.ListNode;

/**
 * @date : 2019/03/25 14:25
 * @author: liangenmao
 */
public class MergeKSortedLists {

    public ListNode result1(ListNode[] lists) {
        ListNode listNode = new ListNode(0);
        ListNode head = listNode;
        int num = 0;
        while (num < lists.length) {
            Integer minNum = null;
            Integer minIndex = null;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    if (minNum == null || lists[i].val < minNum) {
                        minNum = lists[i].val;
                        minIndex = i;
                    }
                }
            }
            if (minIndex == null){
                return head.next;
            }
            listNode.next = lists[minIndex];
            listNode = listNode.next;
            lists[minIndex] = lists[minIndex].next;
            if (lists[minIndex] == null) {
                num++;
            }
        }
        return head.next;
    }

    public  ListNode  mergeKLists(ListNode[] lists) {
        //空数组直接返回
        if (lists == null || lists.length == 0){
            return null;
        }
        //长度为一不用排序
        if (lists.length == 1){
            return lists[0];
        }
        //归并排序
        return merge(lists,0,lists.length-1);
    }

    public  ListNode merge(ListNode[] lists,int l,int r){

        if (l < r){
            //进行拆分
            int mid = (l+r)/2;
            ListNode la = merge(lists, l, mid);
            ListNode lb = merge(lists, mid+1, r);
            //进行合并
            return mergeList(la,lb);
        }
        return lists[l];
    }

    public  ListNode mergeList(ListNode la,ListNode lb){
        ListNode head = new ListNode(0);
        ListNode h = head;
        ListNode p = la;
        ListNode q = lb;
        //关键 进行两个链表的合并
        while (q != null && p != null){
            if (q.val < p.val){
                h.next = q;
                h = q;
                q = q.next;
            } else {
                h.next = p;
                h = p;
                p = p.next;
            }
        }

        //判断哪个链表还剩数据。
        if (p != null){
            h.next = p;
        }else {
            h.next = q;
        }
        return head.next;

    }

    @Test
    public void mergeKLists() {
        ListNode[] lists = new ListNode[]{
                newListNode(1, 4, 5),
                newListNode(1, 3, 4),
                newListNode(2, 6)
        };
        Object result = result1(lists);
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
