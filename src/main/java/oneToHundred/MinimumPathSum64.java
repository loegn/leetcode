package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

/**
 * @date : 2019/04/26 14:52:01
 * @author: liangenmao
 */
public class MinimumPathSum64 {
    public int result1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    public void minPathSum() {
        String ints = "[\n" +
                "  [1,3,1],\n" +
                "  [1,5,1],\n" +
                "  [4,2,1]\n" +
                "]";
        int[][] grid = ParamUtils.getInts(ints);
        Object result = result1(grid);
        PrintUtils.print(result);
    }
}
