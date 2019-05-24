package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/04/19 14:16
 * @author: liangenmao
 */
public class JumpGame55 {

    /**
     * @see JumpGameII45#result3(int[])
     */
    public boolean result1(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int limit = 0;
        int next = 0;

        for (int i = 0; i < nums.length; i++) {
            next = Math.max(next, i + nums[i]);
            if (i == limit) {
                limit = next;
                if (limit >= nums.length - 1) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 从后往前判断该元素是否能到达当前最后，能则置为最后
     */
    public boolean result2(int[] nums) {
        int end = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= end) {
                end = i;
            }
        }
        return end == 0 ? true : false;
    }

    /**
     * 从前往后遍历能达到的最远距离
     */
    public boolean result3(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }

    @Test
    public void canJump() {
        int matrix[] = {2, 5, 0, 0};
        Object result = result2(matrix);
        print(result);
    }

    private void print(Object result) {
        System.out.println(result);
    }
}
