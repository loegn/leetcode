package oneToHundred;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @date : 2019/04/16 14:58
 * @author: liangenmao
 */
public class PermutationsII47 {
    /**
     * DFS
     */
    public List<List<Integer>> result1(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
        Arrays.sort(nums);//Sort the array first
        DFS(nums, new LinkedList<Integer>(), new boolean[nums.length], res);
        return res;
    }

    void DFS(int[] nums, LinkedList<Integer> solution, boolean visited[], LinkedList<List<Integer>> res) {
        if (solution.size() == nums.length) {
            res.add(new LinkedList<Integer>(solution));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //Deal with duplicated numbers, visited[i-1] should be true. (In the same position, only put one distinct number)
            if (visited[i] || (i - 1 >= 0 && visited[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            visited[i] = true;
            solution.add(nums[i]);
            DFS(nums, solution, visited, res);
            solution.remove(solution.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * @see <a href="https://leetcode.com/problems/permutations-ii/discuss/18648/Share-my-Java-code-with-detailed-explanantion"></a>
     */
    public List<List<Integer>> result2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums==null || nums.length==0) { return ans; }
        permute(ans, nums, 0);
        return ans;
    }

    private void permute(List<List<Integer>> ans, int[] nums, int index) {
        if (index == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for (int num: nums) { temp.add(num); }
            ans.add(temp);
            return;
        }
        Set<Integer> appeared = new HashSet<>();
        for (int i=index; i<nums.length; ++i) {
            if (appeared.add(nums[i])) {
                swap(nums, index, i);
                permute(ans, nums, index+1);
                swap(nums, index, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int save = nums[i];
        nums[i] = nums[j];
        nums[j] = save;
    }

    @Test
    public void permuteUnique() {
        int[] nums = {1, 1, 3};
        Object result = result1(nums);
        System.out.println(result);
    }
}
