package oneToHundred;

import java.util.Arrays;

/**
 * @date : 2019/03/12 11:35
 * @author: liangenmao
 */
public class ThreeSumClosest {
    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(target - sum) < Math.abs(target - result)) {
                        result = sum;
                    }
                }
            }
        }
        return result;
    }

    public static int result2(int[] nums, int target) {
        if (nums.length < 3) {
            return Integer.MAX_VALUE;
        }

        Arrays.sort(nums);
        int min_sum = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i <= nums.length - 3; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (target == sum){
                    return target;
                }
                if (target > sum) {
                    j++;
                } else {
                    k--;
                }
                if (Math.abs(sum - target) < Math.abs(min_sum - target)) {
                    min_sum = sum;
                }
            }
        }
        return min_sum;
    }

    public static int result3(int[] nums, int target) {
        if (nums.length == 0 || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);

        int diff = Integer.MAX_VALUE;
        int res = 0;

        for (int i = 0; i < nums.length - 2; i++) {

            if (i == 0 || nums[i] != nums[i - 1]) {

                int lo = i + 1;
                int hi = nums.length - 1;

                while (lo < hi) {
                    int currentVal = nums[i] + nums[lo] + nums[hi];

                    if (currentVal > target) {
                        if (currentVal - target < diff) {
                            diff = currentVal - target;
                            res = currentVal;
                        }
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            hi--;
                        }
                        hi--;

                    } else if (currentVal == target) {
                        return currentVal;
                    } else {
                        if (target - currentVal < diff) {
                            diff = target - currentVal;
                            res = currentVal;
                        }
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            lo++;
                        }
                        lo++;
                    }

                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ints = {1, 1, 1, 1};
        int target = 4;
        int result = threeSumClosest(ints, target);
        System.out.println(result);
    }
}
