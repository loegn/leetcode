package oneToHundred;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @date : 2019/02/19 15:53
 * @author: liangenmao
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String str = "abcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcba";
        long start1 = System.nanoTime();
        String result = result1(str);
        long end1 = System.nanoTime();
        long start2 = System.nanoTime();
        String result2 = result2(str);
        long end2 = System.nanoTime();
        long start3 = System.nanoTime();
        String result3 = result3(str);
        long end3 = System.nanoTime();
        System.out.println(end1 - start1);
        System.out.println(end2 - start2);
        System.out.println(end3 - start3);
    }

    private static String result1(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        String result = "";
        char[] chars = s.toCharArray();
        Map<Integer, Integer> oneChar = new HashMap<>();
        Map<Integer, Integer> twoChar = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            oneChar.put(i, i);
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (chars[i + 1] == chars[i]) {
                twoChar.put(i, i + 1);
            }
        }
        Map<Integer, Map<Integer, Integer>> mapMap = new HashMap<>();
        mapMap.put(1, oneChar);
        mapMap.put(2, twoChar);
        for (int i = 1; i <= s.length() - 2; i++) {
            Map<Integer, Integer> currentMap = mapMap.get(i);
            if (currentMap != null && !currentMap.isEmpty()) {
                Map<Integer, Integer> newMap = new HashMap<>();
                mapMap.put(i + 2, newMap);
                Iterator<Map.Entry<Integer, Integer>> entries = currentMap.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<Integer, Integer> entry = entries.next();
                    int key = entry.getKey();
                    int value = entry.getValue();
                    int start = key - 1;
                    int end = value + 1;
                    if (start >= 0 && end <= s.length() - 1) {
                        if (chars[start] == chars[end]) {
                            newMap.put(start, end);
                        }
                    }
                }
                if (!newMap.isEmpty()) {
                    newMap.remove(currentMap);
                }
            }
        }
        for (int i = s.length(); i >= 1; i--) {
            if (mapMap.get(i) != null && !mapMap.get(i).isEmpty()) {
                Map<Integer, Integer> resultMap = mapMap.get(i);
                Set<Integer> set = resultMap.keySet();
                for (Integer integer : set) {
                    int start = integer;
                    int end = resultMap.get(integer);
                    result = s.substring(start, end + 1);
                    break;
                }
                break;
            }
        }
        return result;
    }

    public static String result2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        //(R - 1) - (L + 1) + 1
        return R - L - 1;
    }

    private static String result3(String s) {
        //-----------------------------------
        //转换字符串
        char[] strChars = new char[(s.length() << 1) + 3];
        strChars[0] = '$';
        strChars[1] = '#';
        for (int i = 0; i < s.length(); i++) {
            strChars[(i << 1) + 2] = (s.charAt(i));
            strChars[(i << 1) + 3] = '#';
        }
        strChars[strChars.length - 1] = '\0';
        //-----------------------------------
        int rightIndex = 0;
        int centerIndex = 0;
        //求len中的最大
        int length = 0;
        //answer最大时的中心
        int index = 0;
        int len[] = new int[strChars.length];
        for (int i = 1; i < strChars.length - 1; i++) {
/*            当rightIndex > i，那么我们就在rightIndex - i 与len[2 * centerIndex - i](len[j])，取得最小值
            因为当i + len[j] < rightIndex时，我们就把len[i]更新为len[j]
            但是如果i + len[j] >= rightIndex时，我们暂且将len[i]定更新为rightIndex - i,超出的部分需要我们一个一个的匹配*/
            if (rightIndex > i) {
                len[i] = Math.min(rightIndex - i, len[2 * centerIndex - i]);
            } else {
                len[i] = 1;
            }
            //一个一个匹配
            //要么是超出的部分，要么是i > rightIndex
            while (strChars[i - len[i]] == strChars[i + len[i]]) {
                len[i]++;
            }
            //当 len[i] + i > rightIndex,我们需要更新centerIndex和rightIndex
            //至于为什么会这样做，理解一下rightIndex和centerIndex的含义
            if (len[i] + i > rightIndex) {
                rightIndex = len[i] + i;
                centerIndex = i;
            }
            if (len[i] > length) {
                length = len[i];
                index = i;
            }
        }
        //截取字符串
        //为什么index - answer + 1,因为len[i] - 1才是原来的回文字符串长度，而answer记录的是len中最大值
//        return stringBuilder.substring(index - answer + 1, index + answer).replace("#", "");
//        if (strChars[index] == '#') {
//            return s.substring((index >> 1) - (len[index] >> 1), (index >> 1) + (len[index] >> 1));
//        } else {
//            return s.substring((index >> 1) - (len[index] >> 1), (index >> 1) + (len[index] >> 1) - 1);
//        }

        int start = (index - length) >> 1;
        return s.substring(start, start + (length - 1));
    }

}
