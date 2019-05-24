package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

/**
 * @date : 2019/04/26 11:15:27
 * @author: liangenmao
 */
public class UniquePaths62 {
    /**
     * 递归方法(leetcode有问题，输入7，3结果会是31)
     */
    public int result1(int m, int n) {
        iMax = m;
        jMax = n;
        helper(1, 1);
        return count;
    }

    private static int count = 0;
    private static int iMax = 1;
    private static int jMax = 1;

    public void helper(int i, int j) {
        if (i == iMax && j == jMax) {
            count++;
            return;
        }
        if (i < iMax) {
            helper(i + 1, j);
        }
        if (j < jMax) {
            helper(i, j + 1);
        }
    }

    /**
     * 动态规划
     * 解题思路：用dp[i][j]表示达到（i, j）这个位置上共有多少条路径。
     * 对于dp[i][j]，最后一步有可能是向下（这时的路径个数就等于dp[i][j-1]）,
     * 也可能是向右（这时的路径个数就等于dp[i-1][j]）
     */
    public int result2(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 数学方法
     * 注意到这是一个组合问题，直接计算C(m+n-2,m-1)即可。时间复杂度为O(n)，空间复杂度O(1)
     */
    public int result3(int m, int n) {
        int N = m + n - 2;
        int M = m < n ? m - 1 : n - 1;
        //计算 C(N,M)
        //根据：C(N,M)=C(N,M-1)*(N-M+1)/M;
        long ans = 1;
        for (int i = 1; i <= M; i++) {
//            ans=ans*(N-i+1)/i;
            ans = ans * (N - M + i) / i;
        }
        return (int) ans;
    }

    @Test
    public void uniquePaths() {
        int m = 3;
        int n = 2;
        Object result = result3(m, n);
        PrintUtils.print(result);
    }
}
