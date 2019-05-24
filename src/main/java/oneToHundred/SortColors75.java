package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

/**
 * @date : 2019/04/29 11:31:21
 * @author: liangenmao
 */
public class SortColors75 {
    /**
     * 一个直观的解决方案是使用计数排序的两趟扫描算法。
     * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
     */
    public void result1(int[] nums) {
        PrintUtils.uncompleted();
    }

    public void result2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int zero = 0;
        int two = nums.length - 1;
        for (int i = zero; i <= two; i++) {
            if (nums[i] == 2) {
                swap(nums, two, i);
                two--;
                i--;
            } else if (nums[i] == 0) {
                swap(nums, zero, i);
                zero++;
            }
        }
    }

    private void swap(int[] nums, int two, int i) {
        int temp = nums[i];
        nums[i] = nums[two];
        nums[two] = temp;
    }

    @Test
    public void sortColors() {
        String ints = "[2,0,2,1,1,0]";
        int[] nums = ParamUtils.getInt(ints);
        result2(nums);
        PrintUtils.print(nums);
    }
}
