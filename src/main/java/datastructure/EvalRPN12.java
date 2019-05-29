package datastructure;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.Stack;

/**
 * @date : 2019/05/29 17:32:16
 * @author: liangenmao
 */
public class EvalRPN12 {
    public int result1(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            Integer curr = convertStringToInt(tokens[i]);
            if (curr != null) {
                stack.push(curr);
            } else {
                int b = stack.pop();
                int a = stack.pop();
                int result;
                switch (tokens[i]) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        result = a / b;
                        break;
                    default:
                        throw new RuntimeException();
                }
                stack.push(result);
            }
        }
        int result = 0;
        return result;
    }

    private Integer convertStringToInt(String string) {
        int result = 0;
        int multiply = 1;
        for (int i = string.length() - 1; i >= 0; i--) {
            int value = string.charAt(i);
            if (value >= '0' || value <= '9') {
                result += multiply * value;
                multiply *= 10;
            } else {
                return null;
            }
        }
        return result;
    }

    @Test
    public void evalRPN() {
        String[] tokens = null;
        Object result = result1(tokens);
        PrintUtils.print(result);
    }
}
