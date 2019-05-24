package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/04/12 11:26
 * @author: liangenmao
 */
public class WildcardMatching44 {
    /**
     * 超时
     */
    public boolean result1(String s, String p) {
        return match(s, 0, p, 0);
    }

    private boolean match(String s, int sIndex, String p, int pIndex) {
        if (pIndex == p.length()) {
            if (sIndex == s.length()) {
                return true;
            } else {
                return false;
            }
        }
        if (p.charAt(pIndex) == SEQUENCE_OF_CHARACTERS) {
            for (int i = sIndex; i <= s.length(); i++) {
                if (match(s, i, p, pIndex + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            if (p.charAt(pIndex) == SINGLE_CHARACTER || (sIndex < s.length() && p.charAt(pIndex) == s.charAt(sIndex))) {
                if (match(s, sIndex + 1, p, pIndex + 1)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    private static final char SINGLE_CHARACTER = '?';
    private static final char SEQUENCE_OF_CHARACTERS = '*';

    /**
     * 动态规划
     */
    public boolean result2(String s, String p) {
        boolean[][] value = new boolean[p.length() + 1][s.length() + 1];
        value[0][0] = true;
        for (int i = 1; i <= s.length(); i++) {
            value[0][i] = false;
        }
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                value[i][0] = value[i - 1][0];
                for (int j = 1; j <= s.length(); j++) {
                    value[i][j] = (value[i][j - 1] || value[i - 1][j]);
                }
            } else if (p.charAt(i - 1) == '?') {
                value[i][0] = false;
                for (int j = 1; j <= s.length(); j++) {
                    value[i][j] = value[i - 1][j - 1];
                }
            } else {
                value[i][0] = false;
                for (int j = 1; j <= s.length(); j++) {
                    value[i][j] = s.charAt(j - 1) == p.charAt(i - 1) && value[i - 1][j - 1];
                }
            }

        }
        return value[p.length()][s.length()];

    }

    /**
     * 有记忆的递归
     */
    public boolean result3(String s, String p) {
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        history = new boolean[sArr.length][pArr.length];
        return recMatch(sArr, pArr, 0, 0);
    }

    boolean[][] history;

    public boolean recMatch(char[] s, char[] p, int nowMatch, int key) {
        if (key >= p.length && nowMatch < s.length) {
            return false;
        }
        if (nowMatch >= s.length) {
            for (int temp = key; temp < p.length; temp++) {
                if (p[temp] != '*') {
                    return false;
                }
            }
            return true;
        }
        if (history[nowMatch][key]) {
            return false;
        }
        history[nowMatch][key] = true;
        boolean b1, b2;
        b1 = b2 = false;
        if (s[nowMatch] == p[key] || p[key] == '?') {
            b1 = recMatch(s, p, nowMatch + 1, key + 1);
        } else if (p[key] == '*') {
            while (key < p.length - 1 && p[key + 1] == '*') {
                key++;
            }
            if (key == p.length - 1) {
                return true;
            }
            b1 = recMatch(s, p, nowMatch, key + 1);
            if (b1) {
                return true;
            }
            while (nowMatch < s.length - 1 && (s[nowMatch + 1] != p[key + 1] && p[key + 1] != '?')) {
                nowMatch++;
            }
            b2 = recMatch(s, p, nowMatch + 1, key);
        } else {
            return false;
        }
        return (b1 || b2);
    }

    @Test
    public void isMatch() {
        String s = "aaaaaaabbbbbbbbbb";
        String p = "a**************b";
        Object result = result2(s, p);
        System.out.println(result);
    }
}
