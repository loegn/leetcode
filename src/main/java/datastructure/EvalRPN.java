package datastructure;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.Stack;

/**
 * @date : 2019/05/29 17:32:16
 * @author: liangenmao
 */
public class EvalRPN {
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
        return stack.peek();
    }

    private Integer convertStringToInt(String string) {
        int result = 0;
        int multiply = 1;
        int first = string.length() > 1 && string.charAt(0) == '-' ? 1 : 0;
        for (int i = string.length() - 1; i >= first; i--) {
            int value = string.charAt(i);
            if (value >= '0' && value <= '9') {
                result += multiply * (value - '0');
                multiply *= 10;
            } else {
                return null;
            }
        }
        return first == 0 ? result : -result;
    }

    @Test
    public void evalRPN() {
        String[] tokens = {"-78", "-33", "196", "+", "-19", "-", "115", "+", "-", "-99", "/", "-18", "8", "*", "-86", "-", "-", "16", "/", "26", "-14", "-", "-", "47", "-", "101", "-", "163", "*", "143", "-", "0", "-", "171", "+", "120", "*", "-60", "+", "156", "/", "173", "/", "-24", "11", "+", "21", "/", "*", "44", "*", "180", "70", "-40", "-", "*", "86", "132", "-84", "+", "*", "-", "38", "/", "/", "21", "28", "/", "+", "83", "/", "-31", "156", "-", "+", "28", "/", "95", "-", "120", "+", "8", "*", "90", "-", "-94", "*", "-73", "/", "-62", "/", "93", "*", "196", "-", "-59", "+", "187", "-", "143", "/", "-79", "-89", "+", "-"};
        Object result = result1(tokens);
        PrintUtils.print(result);
    }
}
