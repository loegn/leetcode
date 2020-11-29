package daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 四数相加 II
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 */
public class No454 {
    /**
     * 分组 + 哈希表
     */
    public int fourSumCount1(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countAB = new HashMap<Integer, Integer>();
        for (int u : A) {
            for (int v : B) {
                countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
            }
        }
        int ans = 0;
        for (int u : C) {
            for (int v : D) {
                if (countAB.containsKey(-u - v)) {
                    ans += countAB.get(-u - v);
                }
            }
        }
        return ans;
    }

    /**
     * 我的解答,该实现的性能略差
     */
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> ab = new HashMap<>();
        for (int i : A) {
            for (int i1 : B) {
                int sum = i + i1;
                ab.put(sum, ab.getOrDefault(sum, 0) + 1);
            }
        }
        Map<Integer, Integer> cd = new HashMap<>();
        for (int i : C) {
            for (int i1 : D) {
                int sum = i + i1;
                cd.put(sum, cd.getOrDefault(sum, 0) + 1);
            }
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : ab.entrySet()) {
            if (cd.containsKey(-entry.getKey())) {
                count += entry.getValue() * cd.get(-entry.getKey());
            }
        }
        return count;
    }
}
