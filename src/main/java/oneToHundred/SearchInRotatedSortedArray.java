package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/03/29 11:42
 * @author: liangenmao
 */
public class SearchInRotatedSortedArray {
    public int result1(int[] nums, int target) {
        return find(nums, 0, nums.length - 1, target);
    }

    /**
     * 不能有重复字段，最大值在左半边且下标为0的值和下标中间的值相等时会出问题
     */
    int find(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = ((end - start) >> 1) + start;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[start] <= nums[mid]) {
            if (target >= nums[start] && target <= nums[mid]) {
                return findByMid(nums, start, mid - 1, target);
            } else {
                return find(nums, mid + 1, end, target);
            }
        } else {
            if (target >= nums[mid] && target <= nums[end]) {
                return findByMid(nums, mid + 1, end, target);
            } else {
                return find(nums, start, mid - 1, target);
            }
        }
    }

    int findByMid(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = ((end - start) >> 1) + start;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] < target) {
            return findByMid(nums, mid + 1, end, target);
        } else {
            return findByMid(nums, start, mid - 1, target);
        }
    }

    @Test
    public void search() {
        int[] nums = {2,3,1,2,2,2,2};
        int target = 1;
        Object result = result1(nums, target);
        System.out.println(result);
    }
}
