package datastructure.array;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

/**
 * @date : 2019/06/13 10:49:16
 * @author: liangenmao
 */
public class PivotIndex {
    public int result1(int[] nums) {
        if (nums.length < 2){
            return -1;
        }
        //中心索引右侧元素之和
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            right += nums[i];
        }
        //中心索引左侧元素之和
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            left += i > 0 ? nums[i - 1] : 0;
            right -= nums[i];
            if (left == right){
                return i;
            }
        }
        return -1;
    }

    @Test
    public void pivotIndex() {
        String ints = "[1, 7, 3, 6, 5, 6]";
        int[] nums = ParamUtils.getInt(ints);
        Object result = result1(nums);
        PrintUtils.print(result);
    }
}
