package daily;

import utils.ParamUtils;
import utils.PrintUtils;

/**
 * https://leetcode-cn.com/problems/rotate-array/
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 */
public class No189 {
    /**
     * 环状替换
     *
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        //a为圈数，b为每圈的元素数量，an=bk
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    /**
     * 最大公约数
     *
     * @param x
     * @param y
     * @return
     */
    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    /**
     * 暴力
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        int length = nums.length;
        for (int i = 0; i < k; i++) {
            for (int j = 1; j < length; j++) {
                int temp = nums[0];
                nums[0] = nums[j];
                nums[j] = temp;
//                nums[0] = nums[0] ^ nums[j];
//                nums[j] = nums[0] ^ nums[j];
//                nums[0] = nums[0] ^ nums[j];
            }
        }
    }

    /**
     * 计算
     *
     * @param nums
     * @param k
     */
    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = 0;
        int start = 0;
        while (count < n) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
            start++;
        }
    }

    public static void main(String[] args) {
        No189 no189 = new No189();
        int[] input = ParamUtils.getInt("[1,2,3,4,5,6]");
        int k = 1;
        no189.rotate2(input, k);
        PrintUtils.print(input);
    }
}
