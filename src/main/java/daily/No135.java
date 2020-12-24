package daily;

import utils.ParamUtils;
import utils.PrintUtils;

/**
 * https://leetcode-cn.com/problems/candy/
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 */
public class No135 {
    /**
     * 两次遍历
     *
     * @param ratings
     * @return
     */
    public int candy1(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 0, ret = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            ret += Math.max(left[i], right);
        }
        return ret;
    }

    /**
     * 常数空间遍历
     *
     * @param ratings
     * @return
     */
    public int candy2(int[] ratings) {
        int n = ratings.length;
        int ret = 1;
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }

    /**
     * 我的错误解答
     *
     * @param ratings
     * @return
     */
    public int candy3(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return -1;
        }
        int length = ratings.length;
        int index = 0;
        for (int i = 1; i < length; i++) {
            if (ratings[i] < ratings[index]) {
                index = i;
            } else {
                break;
            }
        }
        int sum = 1;
        int pre = 1;
        for (int i = index - 1; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                sum += ++pre;
            } else {
                sum += pre = 1;
            }
        }
        pre = 1;
        for (int i = index + 1; i < length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                sum += ++pre;
            } else {
                sum += pre = 1;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        No135 no135 = new No135();
        int[] input = ParamUtils.getInt("[1,3,2,2,1]");
        Object result = no135.candy2(input);
        PrintUtils.print(result);
    }
}
