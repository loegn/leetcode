package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/04/18 10:36
 * @author: liangenmao
 */
public class MaximumSubarray53 {
    public int result1(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            max = Math.max(sum, max);
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    public int result2(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {// 找到最大值
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum == 0 && nums[i] <= 0) {// sum == 0时，负数没有增益，跳过
                continue;
            }
            sum += nums[i]; // sum值可能波动，但只要小于0，就说明已经这个子序列结束了
            max = max > sum ? max : sum;// 更新max
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    /**
     * DP
     */
    public int result3(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int prev = nums[0];
        int res = prev;

        for (int i = 1; i < nums.length; i++) {
            prev = Math.max(prev + nums[i], nums[i]);
            res = Math.max(res, prev);
        }

        return res;
    }

    /**
     * DP
     * divide and conquer
     */
    public int result4(int[] nums) {
        int _sum = 0;
        int _max = Integer.MIN_VALUE;
        for (int n : nums) {
            _sum += n;
            _max = Math.max(_sum, _max);
            if (_sum < 0) {
                _sum = 0;
            }
        }
        return _max;
    }

    @Test
    public void maxSubArray() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Object result = result4(nums);
        print(result);
    }

    private void print(Object result) {
        System.out.println(result);
    }
}
