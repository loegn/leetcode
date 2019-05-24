package oneToHundred;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @date : 2019/04/16 11:24
 * @author: liangenmao
 */
public class Permutations46 {
    /**
     * 我的
     */
    public List<List<Integer>> result1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        add(result, nums, new LinkedHashSet<>());
        return result;
    }

    private void add(List<List<Integer>> result, int[] nums, LinkedHashSet<Integer> tempSet) {
        if (tempSet.size() == nums.length) {
            result.add(new ArrayList(tempSet));
            return;
        }
        for (int num : nums) {
            if (!tempSet.contains(num)) {
                LinkedHashSet<Integer> set = new LinkedHashSet<>(tempSet);
                set.add(num);
                add(result, nums, set);
            }
        }
    }

    public List<List<Integer>> result2(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        recPer(nums, new Integer[nums.length], result, nums.length, 0);
        return result;
    }

    void swap(int[] nums, int A, int B) {
        int temp = nums[A];
        nums[A] = nums[B];
        nums[B] = temp;
    }

    public void recPer(int[] nums, Integer[] lastList, List<List<Integer>> result, int len, int place) {
        for (int temp = 0; temp < len; temp++) {
            lastList[place] = nums[temp];
            if (place + 1 != nums.length) {
                //将已赋值的元素移到当前长度的最后，下次递归不会访问到
                swap(nums, temp, len - 1);                          //把当前元素与数组尾交换
                recPer(nums, lastList, result, len - 1, place + 1);
                swap(nums, temp, len - 1);                         //还原数组
            } else {
                result.add(Arrays.asList(lastList.clone()));
            }
        }
    }

    @Test
    public void permute() {
        int[] nums = {1, 2, 3};
        Object result = result2(nums);
        System.out.println(result);
    }
}
