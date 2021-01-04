package daily;

/**
 * https://leetcode-cn.com/problems/fibonacci-number/
 * 斐波那契数
 * 给你 n ，请计算 F(n)
 */
public class No509 {
    /**
     * 矩阵快速幂
     *
     * @param n
     * @return
     */
    public int fib1(int n) {
        if (n < 2) {
            return n;
        }
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n - 1);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    /**
     * 通项公式
     *
     * @param n
     * @return
     */
    public int fib2(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibN = Math.pow((1 + sqrt5) / 2, n) - Math.pow((1 - sqrt5) / 2, n);
        return (int) Math.round(fibN / sqrt5);
    }

    /**
     * 递归
     *
     * @param n
     * @return
     */
    public int fib3(int n) {
        if (n < 2) {
            return n;
        }
        return fib3(n - 1) + fib3(n - 2);
    }

    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    public int fib4(int n) {
        int length = n + 1;
        int[] dp = new int[length];
        dp[1] = 1;
        for (int i = 2; i < length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 动态规划-优化空间
     *
     * @param n
     */
    public int fib5(int n) {
        int[] value = {0, 1};
        for (int i = 2; i <= n; i++) {
            value[i & 1] = value[0] + value[1];
        }
        return value[n & 1];
    }

    /**
     * 时间复杂度O(1)
     * 本题的n<=30
     *
     * @param n
     * @return
     */
    public int fib6(int n) {
        int nums[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040};
        return nums[n];
    }

    public static void main(String[] args) {
        No509 no509 = new No509();
        Object result = no509.fib4(5);
        System.out.println(result);
    }
}
