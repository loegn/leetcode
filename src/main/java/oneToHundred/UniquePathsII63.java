package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.ParamUtils;
import utils.PrintUtils;

/**
 * @date : 2019/04/26 13:52:35
 * @author: liangenmao
 */
public class UniquePathsII63 {
    /**
     * dp
     */
    public int result1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    public void uniquePathsWithObstacles() {
        String ints = "[\n" +
                "  [0,0,0],\n" +
                "  [0,1,0],\n" +
                "  [0,0,0]\n" +
                "]";
        int[][] obstacleGrid = ParamUtils.getInts(ints);
        Object result = result1(obstacleGrid);
        PrintUtils.print(result);
    }
}
