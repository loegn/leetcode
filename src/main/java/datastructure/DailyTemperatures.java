package datastructure;

import org.junit.jupiter.api.Test;
import utils.ParamUtils;
import utils.PrintUtils;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Stack;

/**
 * @date : 2019/05/29 17:09:30
 * @author: liangenmao
 */
public class DailyTemperatures {
    public int[] result1(int[] T) {
        int[] result = new int[T.length];
        Stack<Map.Entry<Integer, Integer>> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.empty() && stack.peek().getValue() < T[i]) {
                Map.Entry<Integer, Integer> top = stack.pop();
                result[top.getKey()] = i - top.getKey();
            }
            stack.push(new AbstractMap.SimpleEntry<>(i, T[i]));
        }
        return result;
    }

    @Test
    public void dailyTemperatures() {
        String ints = "";
        int[] T = ParamUtils.getInt(ints);
        Object result = result1(T);
        PrintUtils.print(result);
    }
}
