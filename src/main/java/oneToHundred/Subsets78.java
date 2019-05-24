package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.*;

import utils.ParamUtils;

/**
 * @date : 2019/05/07 09:57:13
 * @author: liangenmao
 */
public class Subsets78 {
    public List<List<Integer>> result1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if (nums.length == 0) {
            return result;
        }
        for (int num : nums) {
            int length = result.size();
            for (int i = 0; i < length; i++) {
                List<Integer> list = new ArrayList(result.get(i));
                list.add(num);
                result.add(list);
            }
        }
        return result;
    }

    @Test
    public void subsets() {
        String ints = "[1,2,3]";
        int[] nums = ParamUtils.getInt(ints);
        Object result = result1(nums);
        PrintUtils.print(result);
    }
}
