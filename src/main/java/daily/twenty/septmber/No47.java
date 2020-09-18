package daily.twenty.septmber;

import utils.PrintUtils;

import java.util.*;
import java.util.stream.Collectors;

public class No47 {
    boolean[] vis;

    public List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm);
        return ans;
    }

    public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            vis[i] = false;
            perm.remove(idx);
        }
    }

    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Deque temp = new ArrayDeque();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        dfs(result, temp, map, nums.length);
        return result;
    }

    private void dfs(List<List<Integer>> result, Deque temp, Map<Integer, Integer> map, int length) {
        map.forEach((k, v) -> {
            if (v > 0) {
                temp.addLast(k);
                map.put(k, map.get(k) - 1);
                dfs(result, temp, map, length);
                temp.removeLast();
                map.put(k, map.get(k) + 1);
            }
        });
        if (temp.size() == length) {
            result.add(new ArrayList<>(temp));
        }
    }

    public List<List<Integer>> permuteUnique3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Deque temp = new ArrayDeque();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[][] count = new int[map.size()][];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count[i] = new int[]{entry.getKey(), entry.getValue()};
            i++;
        }
        dfs(result, temp, count, nums.length);
        return result;
    }

    private void dfs(List<List<Integer>> result, Deque temp, int[][] nums, int length) {
        for (int[] count : nums) {
            if (count[1] > 0) {
                temp.addLast(count[0]);
                count[1]--;
                dfs(result, temp, nums, length);
                temp.removeLast();
                count[1]++;
            }
        }
        if (temp.size() == length) {
            result.add(new ArrayList<>(temp));
        }
    }

    public static void main(String[] args) {
        No47 no47 = new No47();
        int[] input = {1, 1, 2};
        PrintUtils.print(no47.permuteUnique1(input));
    }
}
