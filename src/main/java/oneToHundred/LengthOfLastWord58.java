package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/04/22 17:38
 * @author: liangenmao
 */
public class LengthOfLastWord58 {
    public int result1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int end = s.length();
        int start = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                end = i;
                break;
            }
        }
        for (int i = end - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                start = i + 1;
                break;
            }
        }
        if (end == s.length()) {
            return 0;
        } else {
            if (start < 0) {
                return end - 0 + 1;
            } else {
                return end - start + 1;
            }
        }
    }

    public int result2(String s) {
        return s == null ? 0 : ((s = s.trim()).length() - (s.lastIndexOf(' ') < 0 ? 0 : s.lastIndexOf(' ') + 1));
    }

    public int result3(String s) {
        int cnt = 0;
        int tail = s.length() - 1;
        while (tail >= 0 && s.charAt(tail) == ' ') {
            tail -= 1;
        }
        while (tail >= 0 && s.charAt(tail) != ' ') {
            cnt += 1;
            tail -= 1;
        }
        return cnt;
    }

    @Test
    public void lengthOfLastWord() {
        String s = " ";
        Object result = result3(s);
        print(result);
    }

    private void print(Object result) {
        System.out.println(result);
    }
}
