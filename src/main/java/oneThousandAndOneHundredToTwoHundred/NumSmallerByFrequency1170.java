package oneThousandAndOneHundredToTwoHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.Arrays;

/**
 * @date : 2019/08/28 14:50:35
 * @author: liangenmao
 */
public class NumSmallerByFrequency1170 {
    public int[] result1(String[] queries, String[] words) {
        int[] result = new int[queries.length];
        int[] queriesCounts = countStrings(queries);
        int[] wordsCounts = countStrings(words);
        Arrays.sort(wordsCounts);
        for (int i = 0; i < queriesCounts.length; i++) {
            for (int wordsCount : wordsCounts) {
                if (wordsCount > queriesCounts[i]) {
                    result[i]++;
                }
            }
        }
        return result;
    }

    private int f(String s) {
        //26个字母
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (int i : count) {
            if (i > 0) {
                return i;
            }
        }
        return 0;
    }

    private int f2(String s) {
        char min = 'z' + 1;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < min) {
                min = s.charAt(i);
                count = 0;
            }
            if (s.charAt(i) == min) {
                count++;
            }
        }
        return count;
    }

    private int[] countStrings(String[] strings) {
        int[] counts = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            counts[i] = f(strings[i]);
        }
        return counts;
    }

    @Test
    public void numSmallerByFrequency() {
        String[] queries = {"bbb", "cc"};
        String[] words = {"a", "aa", "aaa", "aaaa"};
        Object result = result1(queries, words);
        PrintUtils.print(result);
    }
}
