package oneThousandAndOneHundredToTwoHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.Stack;

/**
 * @date : 2019/07/08 16:56:57
 * @author: liangenmao
 */
public class MaxDepthAfterSplit1111 {
    public int[] result1(String seq) {
        char left = '(';
        int maxDeptLength = 0;
        int[] result = new int[seq.length()];
        Stack<Character> stack = new Stack();
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == left) {
                stack.push(left);
                maxDeptLength = Math.max(maxDeptLength, stack.size());
            } else {
                stack.pop();
            }
        }
        int deptLength = (maxDeptLength + 1) / 2;
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == left) {
                stack.push(left);
                if (stack.size() > deptLength) {
                    result[i] = 1;
                }
            } else {
                if (stack.size() > deptLength) {
                    result[i] = 1;
                }
                stack.pop();
            }
        }
        return result;
    }

    /**
     * 只需要把嵌套情况 '平均' 分配给两个子序列就可以 注意到 seq 是有效括号, 因此可以放心记录当前的左括号数量(一定 >= 0),
     * 如果目前是左括号则数量加 1, 之后若现在有奇数个左括号则给 0, 偶数个左括号给 1
     * 如果目前是右括号, 则抵消掉一个左括号, 若剩余奇数个左括号则给 1, 偶数个则给 0 (和上一行描述相反)
     */
    public int[] result2(String seq) {
        int[] res = new int[seq.length()];
        int left = 0;
        for (int loc = 0; loc < seq.length(); ++loc) {
            if (seq.charAt(loc) == '(') {
                left++;
                if ((left & 1) == 1) {
                    res[loc] = 0;
                } else {
                    res[loc] = 1;
                }
            } else {
                left--;
                if ((left & 1) == 1) {
                    res[loc] = 1;
                } else {
                    res[loc] = 0;
                }
            }
        }
        return res;
    }

    @Test
    public void maxDepthAfterSplit() {
        String seq = "(()())";
        Object result = result2(seq);
        PrintUtils.print(result);
    }
}
