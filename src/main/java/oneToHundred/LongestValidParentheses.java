package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.Stack;

/**
 * @date : 2019/03/26 16:41
 * @author: liangenmao
 */
public class LongestValidParentheses {
    /**
     * 错误，未完成
     */
    public int result1(String s) {
        PrintUtils.error();
        if (s == null) {
            return 0;
        }
        int maxLength = 0;
        int start = 0;
        char leftChar = '(';
        char rightChar = ')';
        while (true) {
            int balance = 0;
            while (start < s.length() - 1 && s.charAt(start) == rightChar) {
                start++;
            }
            if (start >= s.length() - 1) {
                return maxLength;
            }
            for (int i = start; i < s.length(); i++) {
                if (s.charAt(i) == leftChar) {
                    balance--;
                } else {
                    balance++;
                }
                if (balance > 0) {
                    maxLength = i - start;
                    start = i;
                    break;
                }
                if (i == s.length() - 1) {
                    if (balance == 0) {
                        maxLength = Math.max(i - start + 1, maxLength);
                        return maxLength;
                    } else {
                        start++;
                    }
                }
            }
        }
    }

    /**
     * 暴力解法
     */
    public int result2(String s) {
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j+=2) {
                if (isValid(s.substring(i, j))) {
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;
    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.empty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }

    @Test
    public void longestValidParentheses() {
        String s = ")(((((()())()()))()(()))(";
        Object result = result1(s);
        System.out.println(result);
//        result1(nums);
//        System.out.println(JSON.toJSONString(nums));
    }
}
