package daily;

import utils.PrintUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/reorganize-string/
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 */
public class No767 {
    /**
     * 基于最大堆的贪心算法
     */
    public String reorganizeString1(String S) {
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        PriorityQueue<Character> queue = new PriorityQueue<Character>(new Comparator<Character>() {
            @Override
            public int compare(Character letter1, Character letter2) {
                return counts[letter2 - 'a'] - counts[letter1 - 'a'];
            }
        });
        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                queue.offer(c);
            }
        }
        StringBuffer sb = new StringBuffer();
        while (queue.size() > 1) {
            char letter1 = queue.poll();
            char letter2 = queue.poll();
            sb.append(letter1);
            sb.append(letter2);
            int index1 = letter1 - 'a', index2 = letter2 - 'a';
            counts[index1]--;
            counts[index2]--;
            if (counts[index1] > 0) {
                queue.offer(letter1);
            }
            if (counts[index2] > 0) {
                queue.offer(letter2);
            }
        }
        if (queue.size() > 0) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    /**
     * 基于计数的贪心算法
     */
    public String reorganizeString2(String S) {
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        char[] reorganizeArray = new char[length];
        int evenIndex = 0, oddIndex = 1;
        int halfLength = length / 2;
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            while (counts[i] > 0 && counts[i] <= halfLength && oddIndex < length) {
                reorganizeArray[oddIndex] = c;
                counts[i]--;
                oddIndex += 2;
            }
            while (counts[i] > 0) {
                reorganizeArray[evenIndex] = c;
                counts[i]--;
                evenIndex += 2;
            }
        }
        return new String(reorganizeArray);
    }

    public String reorganizeString3(String S) {
        int length = S.length();
        int[][] count = new int[26][2];
        int max = 0;
        for (int i = 0; i < length; i++) {
            max = Math.max(max, ++count[S.charAt(i) - 'a'][0]);
        }
        if (max > (length + 1) / 2) {
            return "";
        }
        //FIXME 下面的代码有错误没有解决，看官方解答吧
        for (int i = 0; i < 26; i++) {
            count[i][1] = i + 'a';
        }
        Arrays.sort(count, Comparator.comparingInt(a -> a[0]));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ) {
            int begin = 25;
            for (int j = 0; j < 2; j++) {
                for (int k = begin; k >= 0; k--) {
                    if (count[k][0] > 0) {
                        begin = k - 1;
                        count[k][0]--;
                        sb.append((char) count[k][1]);
                        i++;
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        No767 no767 = new No767();
        Object result = no767.reorganizeString1("aaabbbcc");
        PrintUtils.print(result);
    }
}

