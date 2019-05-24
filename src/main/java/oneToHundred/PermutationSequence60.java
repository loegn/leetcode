package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @date : 2019/04/25 10:31
 * @author: liangenmao
 */
public class PermutationSequence60 {
    /**
     * 数学方法
     */
    public String result1(int n, int k) {
        if (k > factorial(n) || k < 1) {
            return "";
        }
        LinkedList<Character> linkedList = new LinkedList();
        for (int i = 0; i < n; i++) {
            linkedList.add((char) (i + '1'));
        }
        k -= 1;
        char[] result = new char[n];
        for (int i = n - 1; i >= 0; i--) {
            int factorial = factorial(i);
            int index = k / factorial;
            char value = linkedList.get(index);
            result[n - i - 1] = value;
            linkedList.remove(index);
            k %= factorial;
        }
        return new String(result);
    }

    private int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * 优化代码
     */
    public String result2(int n, int k) {
        StringBuilder sb = new StringBuilder();
        // 候选数字
        List<Integer> candidates = new LinkedList<>();
        // 分母的阶乘数
        int[] factorials = new int[n+1];
        factorials[0] = 1;
        int fact = 1;
        for(int i = 1; i <= n; ++i) {
            candidates.add(i);
            fact *= i;
            factorials[i] = fact;
        }
        k -= 1;
        for(int i = n-1; i >= 0; --i) {
            // 计算候选数字的index
            int index = k / factorials[i];
            sb.append(candidates.remove(index));
            k -= index*factorials[i];
        }
        return sb.toString();
    }

    @Test
    public void getPermutation() {
        int n = 4;
        int k = 9;
        Object result = result1(n, k);
        PrintUtils.print(result);
    }
}
