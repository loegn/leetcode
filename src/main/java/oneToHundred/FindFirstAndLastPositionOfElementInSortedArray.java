package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/03/29 16:23
 * @author: liangenmao
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    int left = -1;
    int right = -1;

    public int[] result1(int[] nums, int target) {
        if (nums != null && nums.length != 0) {
            findByMid(nums, 0, nums.length - 1, target);
        }
        return new int[]{left, right};
    }

    int findByMid(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = ((end - start) >> 1) + start;
        if (nums[mid] < target) {
            return findByMid(nums, mid + 1, end, target);
        } else if (nums[mid] == target) {
            findLeft(nums, start, mid, target);
            findRight(nums, mid, end, target);
            return mid;
        } else {
            return findByMid(nums, start, mid - 1, target);
        }
    }

    private int findRight(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = ((end - start) >> 1) + start;
        if (nums[mid] < target) {
            return findRight(nums, mid + 1, end, target);
        } else if (nums[mid] == target) {
            right = mid;
            if (start < end) {
                findRight(nums, mid + 1, end, target);
            }
            return mid;
        } else {
            return findRight(nums, start, mid - 1, target);
        }
    }

    private int findLeft(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = ((end - start) >> 1) + start;
        if (nums[mid] < target) {
            return findLeft(nums, mid + 1, end, target);
        } else if (nums[mid] == target) {
            left = mid;
            if (start < end) {
                findLeft(nums, start, mid - 1, target);
            }
            return mid;
        } else {
            return findLeft(nums, start, mid - 1, target);
        }
    }

    @Test
    public void searchRange() {
        int[] nums = {2,2};
        int target = 2;
        Object result = result1(nums, target);
        System.out.println(result);
    }
}
