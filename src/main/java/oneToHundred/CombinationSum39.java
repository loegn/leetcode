package oneToHundred;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @date : 2019/04/11 14:39
 * @author: liangenmao
 */
public class CombinationSum39 {
    /**
     * 我的解答
     */
    public List<List<Integer>> result1(int[] candidates, int target) {
        Arrays.sort(candidates);
        nums = new int[candidates.length];
        find(candidates, target, 0);
        return result;
    }

    private List<List<Integer>> result = new ArrayList<>();
    private int[] nums;

    private void find(int[] candidates, int target, int now) {
        if (target == 0) {
            save(candidates, now - 1);
            return;
        }
        if (now > candidates.length - 1) {
            return;
        }
        int maxCount = target / candidates[now];
        if (maxCount <= 0) {
            return;
        }
        for (int i = 0; i <= maxCount; i++) {
            nums[now] = i;
            find(candidates, target - i * candidates[now], now + 1);
        }
    }

    private void save(int[] candidates, int end) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= end; i++) {
            for (int j = 0; j < nums[i]; j++) {
                list.add(candidates[i]);
            }
        }
        result.add(list);
    }

    /**
     * 评论中的答案
     */
    public List<List<Integer>> result2(int[] candidates, int target) {
        List<List<Integer>> listAll = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        //排序
        Arrays.sort(candidates);
        find(listAll, list, candidates, target, 0);
        return listAll;
    }

    public void find(List<List<Integer>> listAll, List<Integer> tmp, int[] candidates, int target, int num) {
        //递归的终点
        if (target == 0) {
            listAll.add(tmp);
            return;
        }
        if (target < candidates[0]) {
            return;
        }
        for (int i = num; i < candidates.length && candidates[i] <= target; i++) {
            //深拷贝
            List<Integer> list = new ArrayList<>(tmp);
            list.add(candidates[i]);
            //递归运算，将i传递至下一次运算是为了避免结果重复。
            find(listAll, list, candidates, target - candidates[i], i);
        }
    }

    @Test
    public void combinationSum() {
        int[] candidates = {2, 3,};
        int target = 3;
        Object result = result1(candidates, target);
        System.out.println(result);
    }
}
