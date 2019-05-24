package oneToHundred;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

/**
 * @date : 2019/03/26 16:14
 * @author: liangenmao
 */
public class NextPermutation {
    public void result1(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    @Test
    public void nextPermutation() {
        int[] nums = {1, 2, 3};
//        Object result = result1(s, words);
//        System.out.println(result);
        result1(nums);
        System.out.println(JSON.toJSONString(nums));
    }
}
