package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

/**
 * @date : 2019/04/11 16:41
 * @author: liangenmao
 */
public class FirstMissingPositive41 {
    /**
     *     错误的解法
     */
    public int result1(int[] nums) {
        PrintUtils.error();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (value >= 1 && value <= nums.length) {
                //交换
                int temp = nums[value - 1];
                nums[value - 1] = value;
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return 1;
    }

    public static int result2(int[] nums) {
        if (nums.length == 0) {
            return 1;
        }
        int res = 1;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] <= 0) {
                nums[i++] = 0;
                continue;
            }
            if (nums[i] > nums.length) {
                nums[i++] = 0;
            } else {
                if (nums[i] - 1 == i) {
                    i++;
                    continue;
                }
                if (nums[nums[i] - 1] == nums[i]) {
                    nums[i++] = 0;
                    continue;
                }
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int j = 0; j < nums.length; j++) {
            res++;
            if (nums[j] == 0) {
                res = j + 1;
                break;
            }
        }
        return res;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void firstMissingPositive() {
        int[] candidates = {3, 4, -1, 1};
        Object result = result2(candidates);
        System.out.println(result);
    }

}
