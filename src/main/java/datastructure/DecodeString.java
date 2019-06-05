package datastructure;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.List;
import java.util.Stack;

/**
 * @date : 2019/06/03 14:05:51
 * @author: liangenmao给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例:
 * <p>
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 */
public class DecodeString {
    public String result1(String s) {
        String res = "";
        Stack<Integer> nums = new Stack<>();
        Stack<String> strs = new Stack<>();
        int num = 0;
        int len = s.length();
        for (int j = 0; j < len; ++j) {
            if (Character.isDigit(s.charAt(j))) {
                num = num * 10 + s.charAt(j) - '0';
            } else if (Character.isLetter(s.charAt(j))) {
                res = res + s.charAt(j);
            } else if (s.charAt(j) == '[') {
                //将‘[’前的数字压入nums栈内， 字母字符串压入strs栈内
                nums.push(num);
                num = 0;
                strs.push(res);
                res = "";
            } else {
                //遇到‘]’时，操作与之相配的‘[’之间的字符，使用分配律
                int times = nums.peek();
                nums.pop();
                for (int k = 0; k < times; ++k) {
                    strs.push(strs.pop() + res);
                }
                //之后若还是字母，就会直接加到res之后，因为它们是同一级的运算
                //若是左括号，res会被压入strs栈，作为上一层的运算
                res = strs.pop();
            }
        }
        return res;
    }

    /**
     * 如果遇到 ']'，就一直在栈中找到 '['，将之间的字符连接起来，
     * 将 '['前面的数字连接起来作为出现次数再次压栈，
     * 遇到数字、字母、'['就直接压栈，
     * 最后将栈里的字符串弹出连接起来就ok了
     */
    public String result2 (String s) {
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                String string = "";
                while (!stack.peek().equals("[")) {
                    string = stack.pop() + string;
                }
                stack.pop();
                String countString = "";
                while ((!stack.isEmpty()) && (stack.peek().charAt(0) >= '0' && stack.peek().charAt(0) <= '9')) {
                    countString = stack.pop() + countString;
                }
                int count = Integer.parseInt(countString);
                String retString = "";
                for (int j = 0; j < count; j++) {
                    retString = retString + string;
                }
                stack.push(retString);
            } else {
                String str = "" + s.charAt(i);
                stack.push(str);
            }
        }
        String result = "";
        while (!stack.isEmpty()) {
            result = stack.pop() + result;
        }
        return result;
    }

    @Test
    public void decodeString() {
        String s = null;
        Object result = result1(s);
        PrintUtils.print(result);
    }
}
