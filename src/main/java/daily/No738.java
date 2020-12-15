package daily;

import utils.PrintUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 */
public class No738 {
    /**
     * 贪心算法
     *
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits1(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length) {
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }

    public static void main(String[] args) {
        No738 no738 = new No738();
        int input = 332;
        Object result = no738.monotoneIncreasingDigits1(input);
        PrintUtils.print(result);
    }
}
