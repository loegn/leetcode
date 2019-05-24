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
        ListNode listNode = this;
        StringBuilder sb = new StringBuilder();
        String separator = "->";
        while (listNode != null) {
            sb.append(listNode.val);
            sb.append(separator);
            listNode = listNode.next;
        }
        sb.setLength(sb.length() - separator.length());
        return sb.toString();
    }
}
