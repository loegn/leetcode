package daily;

import utils.ParamUtils;
import utils.PrintUtils;

/**
 * https://leetcode-cn.com/problems/min-cost-climbing-stairs/
 * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 */
public class No746 {
    /**
     * 动态规划
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    /**
     * 动态规划-滚动数组优化
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int prev = 0, curr = 0;
        for (int i = 2; i <= n; i++) {
            int next = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
            prev = curr;
            curr = next;
        }
        return curr;
    }

    /**
     * 我的解答
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs3(int[] cost) {
        int length = cost.length;
        //到达每节楼梯最小的体力消耗
        int[] min = new int[length];
        min[0] = cost[0];
        min[1] = cost[1];
        for (int i = 2; i < length; i++) {
//            min[i] = Math.min(min[i - 2] + cost[i], min[i - 1] + cost[i]);
            min[i] = Math.min(min[i - 2], min[i - 1]) + cost[i];
        }
        //从最后一节楼梯或倒数第二节楼梯可以不消耗体力直接到达楼顶
        return Math.min(min[length - 2], min[length - 1]);
    }

    public static void main(String[] args) {
        No746 no746 = new No746();
        int[] input = ParamUtils.getInt("[10, 15, 20]");
        Object result = no746.minCostClimbingStairs3(input);
        PrintUtils.print(result);
    }
}
