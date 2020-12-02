package daily;

import utils.ParamUtils;

/**
 * https://leetcode-cn.com/problems/create-maximum-number/
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 */
public class No321 {
    /**
     * 单调栈解法
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        //start m子串的最小长度，end m子串的最大长度
        int start = Math.max(0, k - n), end = Math.min(k, m);
        for (int i = start; i <= end; i++) {
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    /**
     * 寻找指定长度的最大子串
     *
     * @param nums 原数组
     * @param k    最大子串的长度
     * @return 指定长度的最大子串
     */
    public int[] maxSubsequence(int[] nums, int k) {
        int length = nums.length;
        /** 单调栈 */
        int[] stack = new int[k];
        /** 栈顶下标 */
        int top = -1;
        /** 原数组剩余可以不要的值的数量,确保最后子串长度能达到k*/
        int remain = length - k;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            /** 如果栈不为空且栈顶元素小于当前元素且原数组还有剩余可不要的值 */
            while (top >= 0 && stack[top] < num && remain > 0) {
                /** 出栈 */
                top--;
                /** 原数组剩余可以不要的值的数量减少一个 */
                remain--;
            }
            /** 如果栈的长度没有超过要求的子串长度 */
            if (top < k - 1) {
                /** 入栈 */
                stack[++top] = num;
            } else {
                /** 可用数量减一,继续查找下一个元素看是否能替换栈顶元素 */
                remain--;
            }
        }
        return stack;
    }

    /**
     * 融合两个数为最大的数
     *
     * @param subsequence1
     * @param subsequence2
     * @return
     */
    public int[] merge(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            /** 将较大那个子串中的当前数添加到新数组中*/
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }

    /**
     * 比较两个数组子串的大小
     * @param subsequence1
     * @param index1 子串的开始下标
     * @param subsequence2
     * @param index2 子串的开始下标
     * @return
     */
    public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }

    public static void main(String[] args) {
        No321 no321 = new No321();
        int[] input1 = ParamUtils.getInt("[3, 4, 6, 5]");
        int[] input2 = ParamUtils.getInt("[9, 1, 2, 5, 8, 3]");
        int k = 5;
        no321.maxNumber(input1, input2, k);
    }
}
