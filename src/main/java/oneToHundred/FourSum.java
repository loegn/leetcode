package oneToHundred;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @date : 2019/03/13 10:38
 * @author: liangenmao
 */
public class FourSum {
    public List<List<Integer>> result(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                //去重
                if (nums[j] == nums[j - 1] && j > i + 1) {
                    continue;
                }
                int k = j + 1;
                int l = nums.length - 1;
                while (k < l) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum == target) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                        result.add(list);
                    }
                    if (sum < target) {
                        //去重
                        do {
                            k++;
                        } while (nums[k] == nums[k - 1] && k < l);
                    } else {
                        //去重
                        do {
                            l--;
                        } while (nums[l] == nums[l + 1] && k < l);
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void fourSum() {
        int[] nums = {5,5,3,5,1,-5,1,-2};
        int target = 4;
        result(nums, target);
        System.out.println();
    }
}
