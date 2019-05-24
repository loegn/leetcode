package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/04/11 13:59
 * @author: liangenmao
 */
public class CountAndSay38 {
    public String result1(int n) {
        String one = "1";
        for (int i = 1; i < n; i++) {
            one = call(one);
        }
        return one;
    }

    private String call(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                count++;
            } else {
                sb.append(count);
                sb.append(chars[i] - '0');
                count = 1;
            }
        }
        sb.append(count);
        sb.append(chars[chars.length - 1] - '0');
        return sb.toString();
    }

    @Test
    public void countAndSay() {
        int n = 1;
        Object result = result1(n);
        System.out.println(result);
    }
}
