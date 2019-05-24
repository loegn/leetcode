package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

/**
 * @date : 2019/04/28 15:11:33
 * @author: liangenmao
 */
public class ClimbingStairs70 {
    /**
     * 超时
     */
    public int result1(int n) {
        if (n <= 0) {
            return -1;
        }
        climb(0, n);
        return this.result;
    }

    private int result = 0;

    private void climb(int curr, int n) {
        if (curr >= n) {
            if (curr == n) {
                result++;
            }
            return;
        }
        climb(curr + 1, n);
        climb(curr + 2, n);
    }

    public int result2(int n) {
        if (n <= 0) {
            return -1;
        }
        int[] result = new int[n + 1];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i <= n; i++) {
            result[i] = result[i - 2] + result[i - 1];
        }
        return result[n];
    }

    /**
     *  fibonacci
     * @see #result2(int)
     */
    public int result3(int n) {
        double d = Math.sqrt(5);
        n = n + 1;
        return (int) ((Math.pow((1 + d) / 2, n) - Math.pow((1 - d) / 2, n)) / d + 0.5);
    }

    /**
     * C(n,0)+...+C(n,n/2)
     */
//    public int result4(int n){
//
//    }

    @Test
    public void climbStairs() {
        int n = 2;
        Object result = result2(n);
        PrintUtils.print(result);
    }
}
