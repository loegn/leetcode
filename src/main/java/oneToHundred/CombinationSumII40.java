package oneToHundred;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @date : 2019/04/11 15:40
 * @author: liangenmao
 */
public class CombinationSumII40 {
    public List<List<Integer>> result1(int[] candidates, int target) {
        Arrays.sort(candidates);
        origin = candidates;
        nums = new boolean[candidates.length];
        find(target, candidates.length - 1);
        return result;
    }

    private List<List<Integer>> result = new ArrayList<>();
    private boolean[] nums;
    private int[] origin;

    private void find(int target, int index) {
        if (target == 0) {
            save(index);
            return;
        }
        if (index < 0) {
            return;
        }
        while (index < origin.length - 1 && origin[index] == origin[index + 1] && !nums[index + 1]) {
            index--;
            if (index < 0) {
                return;
            }
        }
        if (origin[index] <= target) {
            nums[index] = true;
            find(target - origin[index], index - 1);
        }
        nums[index] = false;
        find(target, index - 1);
    }

    private void save(int index) {
        List<Integer> list = new ArrayList<>();
        int[] ints = origin;
        for (int i = ints.length - 1; i > index; i--) {
            if (nums[i]) {
                list.add(ints[i]);
            }
        }
        result.add(list);
    }

    @Test
    public void combinationSum2() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        Object result = result1(candidates, target);
        System.out.println(result);
    }
}
