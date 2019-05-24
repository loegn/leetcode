package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/04/03 17:19
 * @author: liangenmao
 */
public class SearchInsertPosition {

    private int index = -1;

    public int result1(int[] nums, int target) {
        int result = findByMid(nums, 0, nums.length - 1, target);
        return result > -1 ? result : index;
    }

    int findByMid(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = ((end - start) >> 1) + start;
        this.index = mid;
        if (nums[mid] < target) {
            return findByMid(nums, mid + 1, end, target);
        } else if (nums[mid] == target) {
            return mid;
        } else {
            return findByMid(nums, start, mid - 1, target);
        }
    }

    /**
     * 暴力解法
     */
    public int result2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     *  二分法
     */
    public int result3(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    @Test
    public void searchInsert() {
        int[] nums = {1, 3, 5, 6};
        int target = 2;
        Object result = result3(nums, target);
        System.out.println(result);
    }
}
