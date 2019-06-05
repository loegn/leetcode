package datastructure;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

/**
 * @date : 2019/05/30 10:01:41
 * @author: liangenmao
 */
public class FindTargetSumWays {
    private int count = 0;

    public int result1(int[] nums, int S) {
        helper(nums, S, 0, 0);
        return count;
    }

    private void helper(int[] nums, int target, int sum, int index) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
            return;
        }
        int value = nums[index];
        helper(nums, target, sum + value, index + 1);
        helper(nums, target, sum - value, index + 1);
    }

    @Test
    public void findTargetSumWays() {
        String ints = "[1,1,1,1,1]";
        int[] nums = ParamUtils.getInt(ints);
        int S = 3;
        Object result = result1(nums, S);
        PrintUtils.print(result);
    }
}
