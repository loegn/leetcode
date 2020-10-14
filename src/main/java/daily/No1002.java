package daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No1002 {
    public List<String> commonChars1(String[] A) {
        int[] minfreq = new int[26];
        Arrays.fill(minfreq, Integer.MAX_VALUE);
        for (String word : A) {
            int[] freq = new int[26];
            int length = word.length();
            for (int i = 0; i < length; ++i) {
                char ch = word.charAt(i);
                ++freq[ch - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                minfreq[i] = Math.min(minfreq[i], freq[i]);
            }
        }

        List<String> ans = new ArrayList<String>();
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < minfreq[i]; ++j) {
                ans.add(String.valueOf((char) (i + 'a')));
            }
        }
        return ans;
    }

    public List<String> commonChars2(String[] A) {
        List<String> result = new ArrayList<>();
        int letterCount = 26;
        int[] max = new int[letterCount];
        Arrays.fill(max, Integer.MAX_VALUE);
        int[] curr = new int[letterCount];
        for (String s : A) {
            Arrays.fill(curr, 0);
            for (int i = 0; i < s.length(); i++) {
                char letter = s.charAt(i);
                curr[letter - 'a']++;
            }
            for (int i = 0; i < letterCount; i++) {
                max[i] = Math.min(max[i], curr[i]);
            }
        }
        for (int i = 0; i < letterCount; i++) {
            for (int j = 0; j < max[i]; j++) {
                result.add(new String(new char[]{(char) ('a' + i)}));
            }
        }
        return result;
    }
}
