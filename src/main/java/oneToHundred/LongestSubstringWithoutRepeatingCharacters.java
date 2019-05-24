package oneToHundred;

import java.util.HashSet;
import java.util.Set;

/**
 * @date : 2019/01/17 08:50
 * @author: liangenmao
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        lengthOfLongestSubstring("abba");
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n - ans; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s, j - 1, j, set)) {
                    ans = Math.max(ans, j - i);
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    public static boolean allUnique(String s, int start, int end, Set set) {
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch))
                return false;
            set.add(ch);
        }
        return true;
    }
}
