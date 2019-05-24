package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @date : 2019/05/06 16:40:41
 * @author: liangenmao
 */
public class Combinations77 {
    public List<List<Integer>> result1(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new Integer[k], 0, 1, n, k);
        return result;
    }

    private void helper(List<List<Integer>> result, Integer[] ints, int currIndex, int startNum, int n, int k) {
        if (k == 0) {
            List<Integer> list = new ArrayList(ints.length);
            Collections.addAll(list, ints);
            result.add(list);
            return;
        }
        for (int i = startNum; i <= n - k + 1; i++) {
            ints[currIndex] = i;
            helper(result, ints, currIndex + 1, i + 1, n, k - 1);
        }
    }

    public List<List<Integer>> result2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(n < k){
            return res;
        }
        backtrack(res, new ArrayList<>(), n, k, 1);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> temp, int n, int k , int start){
        if(k == 0){
            res.add(new ArrayList<>(temp));
        }
        else{
            for(int i = start; i <= n - k + 1; i++){
                temp.add(i);
                backtrack(res, temp, n, k-1, i+1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    @Test
    public void combine() {
        int n = 4;
        int k = 2;
        Object result = result1(n, k);
        PrintUtils.print(result);
    }
}
