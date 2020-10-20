package pojo;

/**
 * @date : 2019/04/25 16:17
 * @author: liangenmao
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        ListNode cycleNode = cycle(this);
        ListNode listNode = this;
        StringBuilder sb = new StringBuilder();
        String separator = "->";
        boolean end = false;
        while (listNode != null) {
            sb.append(listNode.val);
            if (listNode == cycleNode) {
                sb.append("(cycle)");
                if (end) {
                    break;
                } else {
                    end = true;
                }
            }
            sb.append(separator);
            listNode = listNode.next;
        }
        if (cycleNode == null) {
            sb.setLength(sb.length() - separator.length());
        }
        return sb.toString();
    }

    private ListNode cycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        do {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while (slow != fast);
        while (slow != head) {
            slow = slow.next;
            head = head.next;
        }
        return slow;
    }
}
