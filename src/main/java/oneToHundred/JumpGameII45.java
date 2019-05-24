package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

/**
 * @date : 2019/04/12 15:42
 * @author: liangenmao
 */
public class JumpGameII45 {
    /**
     * 超时
     */
    public int result1(int[] nums) {
        jump(nums, 0, 0);
        return min;
    }

    private void jump(int[] nums, int index, int time) {
        if (index >= nums.length - 1) {
            min = Math.min(min, time);
            return;
        }
        for (int i = 1; i <= nums[index]; i++) {
            jump(nums, index + i, time + 1);
        }
    }

    private int min = Integer.MAX_VALUE;

    /**
     * 超时
     */
    public int result2(int[] nums) {
        times = new int[nums.length];
        for (int i = 1; i < times.length; i++) {
            times[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    times[i] = Math.min(times[i], times[j] + 1);
                }
            }
        }
        return times[nums.length - 1];
    }

    private int[] times;

    /**
     *  找到每次跳跃所能到达的最远距离
     */
    public int result3(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int steps = 0;
        int limit = 0;
        int next = 0;

        for (int i = 0; i < nums.length; i++) {
            next = Math.max(next, i + nums[i]);
            if (i == limit) {
                steps++;
                limit = next;
                if (limit >= nums.length - 1) {
                    return steps;
                }
            }
        }

        return steps;
    }

    /**
     * 我的错误解
     */
    public int result4(int[] nums) {
        PrintUtils.error();
        int time = 0;
        if (nums.length <= 1){
            return time;
        }
        loop : for (int i = 0; i < nums.length; ) {
            time++;
            if (i + nums[i] >= nums.length - 1) {
                return time;
            }
            for (int j = i + 1; j <= i + nums[i]; j++) {
                if (nums[j] > nums[i] - (j - i)) {
                    i = j;
                    continue loop;
                }
            }
            i = i + nums[i];
            while (nums[i] == 0) {
                i--;
            }
        }
        return time;
    }

    @Test
    public void jump() {
        int[] nums = {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3};
        Object result = result3(nums);
        System.out.println(result);
    }
}
