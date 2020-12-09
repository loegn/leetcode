package daily;

import utils.PrintUtils;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 */
public class No62 {
    /**
     * 动态规划
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths1(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }

    /**
     * 动态规划 滚动数组优化空间至O(min(m,n))
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        if (n > m) {
            m ^= n;
            n ^= m;
            m ^= n;
        }
        int[][] f = new int[2][n];
        Arrays.fill(f[0], 1);
        f[1][0] = 1;
        for (int i = 1; i < m; ++i) {
            int curr = i & 1;
            int pre = curr ^ 1;
            for (int j = 1; j < n; ++j) {
                f[curr][j] = f[pre][j] + f[curr][j - 1];
            }
        }
        return f[(m - 1) & 1][n - 1];
    }

    /**
     * 组合数学
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths3(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        No62 no62 = new No62();
        int m = 7;
        int n = 3;
        int result = no62.uniquePaths2(m, n);
        PrintUtils.print(result);
    }
}
