package fiveThousandAndOneHundredToTwoHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

import java.util.Stack;

/**
 * @date : 2019/07/15 16:41:09
 * @author: liangenmao
 */
public class LongestWPI5129 {
    /**
     * 超时
     */
    public int result1(int[] hours) {
        //大于division为劳累
        int division = 8;
        int result = 0;
        for (int i = 0; i < hours.length; i++) {
            int length = 0;
            //>0劳累，<0不劳累
            int balance = 0;
            for (int j = i; j < hours.length; j++) {
                if (hours[j] > division) {
                    balance++;
                } else {
                    balance--;
                }
                if (balance > 0) {
                    length = j - i + 1;
                }
            }
            result = Math.max(length, result);
        }
        return result;
    }


    /**
     * @param hours
     * @return
     * @see #result1(int[])
     * 稍微优化过，没超时。。
     */
    public int result2(int[] hours) {
        int l = hours.length;
        int s[] = new int[l + 1];
        for (int i = 0; i < l; ++i) {
            s[i + 1] = s[i] + ((hours[i] > 8) ? 1 : 0);
            //System.out.println(hs[i]);
        }
        int res = 0;
        for (int i = 0; i < l; ++i) {
            for (int j = i; j < l; ++j) {
                int tot = j - i + 1;
                if (tot <= res) {
                    continue;
                }
                int lao = s[j + 1] - s[i];
                if (lao > tot - lao) {
                    res = Math.max(res, tot);
                }
            }
        }
        return res;
    }

    public int result3(int[] hours) {
        int n = hours.length;
        //大于8小时计1分 小于等于8小时计 -1 分
        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            if (hours[i] > 8) {
                score[i] = 1;
            } else {
                score[i] = -1;
            }
        }
        //前缀和
        int[] presum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            presum[i] = presum[i - 1] + score[i - 1];
        }
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        //顺序生成单调栈，栈中元素从第一个元素开始严格单调递减，最后一个元素肯定是数组中的最小元素所在位置
        for (int i = 0; i < n + 1; i++) {
            if (stack.isEmpty() || presum[stack.peek()] > presum[i]) {
                stack.push(i);
            }
        }
        //倒序扫描数组，求最大长度坡
        int i = n;
        while (i > ans) {
            while (!stack.isEmpty() && presum[stack.peek()] < presum[i]) {
                ans = Math.max(ans, i - stack.pop());
            }
            i--;
        }
        return ans;
    }

    @Test
    public void longestWPI() {
        String ints = "[9,9,6,0,6,6,9]";
        int[] hours = ParamUtils.getInt(ints);
        Object result = result3(hours);
        PrintUtils.print(result);
    }
}
