package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

/**
 * @date : 2019/04/26 15:11:42
 * @author: liangenmao
 */
public class ValidNumber65 {
    /**
     * 错误，不想改了 23.应该为true
     */
    public boolean result1(String s) {
        PrintUtils.error();
        s = s.trim();
        int index = s.lastIndexOf("e");
        String str = s;
        if (index > 0) {
            str = s.substring(index);
            if (str.length() == 1 || !str.matches("e[0-9]+")) {
                return false;
            }
            str = s.substring(0, index);
        }
        if (str.length() == 0 || !str.matches("[+-]?[0-9]*(\\.[0-9]+)?")) {
            return false;
        }
        return true;
    }

    public boolean result2(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return false;
        }
        //e之前为1，e之后为2
        boolean num1 = false;
        boolean num2 = false;
        char numS = '0';
        char numE = '9';
        boolean sign1 = false;
        boolean sign2 = false;
        char signP = '+';
        char signN = '-';
        boolean point = false;
        char pointC = '.';
        //为true后只能为数字
        boolean e = false;
        char eC = 'e';
        char curr;
        for (int i = 0; i < s.length(); i++) {
            curr = s.charAt(i);
            if (curr >= numS && curr <= numE) {
                if (e) {
                    num2 = true;
                } else {
                    num1 = true;
                }
            } else if (curr == signP || curr == signN) {
                //符号只能在开头且只能出现一次,且不能出现在最后
                if (!e) {
                    if (sign1 || num1 || point || i == s.length() - 1) {
                        return false;
                    }
                    sign1 = true;
                } else {
                    if (sign2 || num2 || i == s.length() - 1) {
                        return false;
                    }
                    sign2 = true;
                }
            } else if (curr == pointC) {
                //小数点只能出现在e之前
                if (point || e || (!num1 && i == s.length() - 1)) {
                    return false;
                }
                point = true;
            } else if (curr == eC) {
                //e只能出现一次,且之前一定要出现过数字,且不能出现在最后
                if (e || !num1 || i == s.length() - 1) {
                    return false;
                }
                e = true;
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    public void isNumber() {
        String s = "0";
        Object result = result2(s);
        PrintUtils.print(result);
    }
}
