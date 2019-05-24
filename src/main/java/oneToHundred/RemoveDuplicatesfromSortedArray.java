package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/03/25 17:11
 * @author: liangenmao
 */
public class RemoveDuplicatesfromSortedArray {
    public int result1(int[] nums) {
        int curr = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[curr]) {
                curr++;
                nums[curr] = nums[i];
            }
        }
        return nums.length == 0 ? 0 : curr + 1;
    }

    @Test
    public void removeDuplicates() {
        int[] ints = new int[]{
                0, 0, 1, 1, 1, 2, 2, 3, 3, 4
        };
        Object result = result1(ints);
        System.out.println(result);
    }
}
