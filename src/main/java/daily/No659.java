package daily;

import utils.ParamUtils;
import utils.PrintUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 */
public class No659 {
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();
        for (int x : nums) {
            if (!map.containsKey(x)) {
                map.put(x, new PriorityQueue<Integer>());
            }
            if (map.containsKey(x - 1)) {
                int prevLength = map.get(x - 1).poll();
                if (map.get(x - 1).isEmpty()) {
                    map.remove(x - 1);
                }
                map.get(x).offer(prevLength + 1);
            } else {
                map.get(x).offer(1);
            }
        }
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : entrySet) {
            PriorityQueue<Integer> queue = entry.getValue();
            if (queue.peek() < 3) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        No659 no659 = new No659();
//        int[] input = ParamUtils.getInt("[1,2,3,3,4,4,5,5]");
//        Object result = no659.isPossible(input);
//        PrintUtils.print(result);
        BigDecimal bigDecimal = new BigDecimal(2);
        BigDecimal divisor = bigDecimal.pow(256);
        BigDecimal dividend = divisor.add(new BigDecimal(-1));
        BigDecimal multiplier = new BigDecimal(2).pow(128);
        BigDecimal result = dividend.divide(divisor).pow(multiplie);
        System.out.println(result);
    }
}
