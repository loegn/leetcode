package oneToHundred;

import java.util.HashMap;
import java.util.Map;

/**
 * @date : 2019/02/27 14:12
 * @author: liangenmao
 */
public class RomanToInteger {
    public static void main(String[] args) {
        int result = romanToInt("");
        System.out.println(result);
    }

    public static int romanToInt(String s) {
        int result = 0;
        char[] chars = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] ints = {1, 5, 10, 50, 100, 500, 1000};
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], i);
        }
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                result -= ints[map.get(s.charAt(i))];
            } else {
                result += ints[map.get(s.charAt(i))];
            }
        }
        return result;
    }
}
