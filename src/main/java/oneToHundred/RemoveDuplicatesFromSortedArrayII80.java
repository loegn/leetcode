package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

/**
 * @date : 2019/05/07 15:27:44
 * @author: liangenmao
 */
public class RemoveDuplicatesFromSortedArrayII80 {
    public int result1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int count = 1;
        int length = 1;
        for (int i = 1; i < nums.length; i++, length++) {
            if (nums[i] == nums[i - 1]) {
                if (count == 2) {
                    length--;
                } else {
                    count++;
                }
            } else {
                count = 1;
            }
            nums[length] = nums[i];
        }
        return length;
    }

    /**
     * 少代码
     */
    public int result2(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n != nums[i-2])
                nums[i++] = n;
        return i;
    }
    @Test
    public void removeDuplicates() {
        String ints = "[1,1,1,2,2,3]";
        int[] nums = ParamUtils.getInt(ints);
        Object result = result1(nums);
        PrintUtils.print(result);
    }
}
