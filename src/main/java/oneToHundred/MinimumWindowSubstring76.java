package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.*;

/**
 * @date : 2019/04/30 15:49:44
 * @author: liangenmao
 */
public class MinimumWindowSubstring76 {
    /**
     * 暴力算法
     */
    public String result1(String s, String t) {
        PrintUtils.error("题目不清楚 结果要包含所有t中的字母及数量");
        String result = s;
        boolean flag = false;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < t.length(); i++) {
            set.add(t.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (j - i <= result.length()) {
                    String str = s.substring(i, j);
                    if (match(str, set)) {
                        result = str;
                        flag = true;
                    }
                }
            }
        }
        return flag ? result : "";
    }

    private boolean match(String s, Set<Character> sets) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        for (Character character : sets) {
            if (!set.contains(character)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 超时
     */
    public String result2(String s, String t) {
        String result = s;
        boolean flag = false;
        Map<Character, Integer> map = getMap(t);
        for (int i = 0; i <= s.length() - t.length(); i++) {
            for (int j = i + t.length(); j <= s.length(); j++) {
                if (!flag || j - i < result.length()) {
                    String str = s.substring(i, j);
                    if (match(str, map)) {
                        result = str;
                        flag = true;
                    }
                }
            }
        }
        return flag ? result : "";
    }

    private Map<Character, Integer> getMap(String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            Character character = t.charAt(i);
            if (map.get(character) == null) {
                map.put(character, 0);
            }
            map.put(character, map.get(character) + 1);
        }
        return map;
    }

    private boolean match(String s, Map<Character, Integer> map) {
        Map<Character, Integer> sMap = getMap(s);
        Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            Integer value = sMap.get(entry.getKey());
            if (value == null || value < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public String result3(String s, String t) {
        return "";
    }

    @Test
    public void minWindow() {
        String s = "a";
        String t = "a";
        Object result = result2(s, t);
        PrintUtils.print(result);
    }
}
