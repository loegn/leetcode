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
        ListNode result = null;
        return result;
    }

    @Test
    public void partition() {
        String listNode = "";
        ListNode head = ParamUtils.getListNode(listNode);
        int x = 0;
        Object result = result1(head, x);
        PrintUtils.print(result);
    }
}
