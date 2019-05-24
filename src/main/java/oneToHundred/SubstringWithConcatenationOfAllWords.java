package oneToHundred;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @date : 2019/03/26 13:58
 * @author: liangenmao
 */
public class SubstringWithConcatenationOfAllWords {
    public List<Integer> result1(String s, String[] words) {
        if (s == null || s.length() == 0 || words == null || words.length == 0 || words[0].length() == 0) {
            return Collections.emptyList();
        }
        List<Integer> result = new LinkedList<>();
        int length = words[0].length();
        //匹配的word在s中的下标和在words中的下标
        Map<Integer, String> wordAtSIndex = new HashMap<>();
        //words中的字符串对应的数量
        Map<String, Integer> wordNum = new HashMap();
        /**
         * 填充wordAtSIndex和wordNum
         */
/*        for (int i = 0; i <= s.length() - length; i++) {
            loop:
            for (int j = 0; j < words.length; j++) {
                for (int k = 0; k < length; k++) {
                    if (words[j].charAt(k) != s.charAt(i + k)) {
                        break;
                    }
                    if (k == length - 1) {
                        wordAtSIndex.put(i, words[j]);
                        break loop;
                    }
                }
            }
        }
        for (int i = 0; i < words.length; i++) {
            Integer vaule = wordNum.get(words[i]);
            if (vaule == null) {
                wordNum.put(words[i], 1);
            } else {
                wordNum.put(words[i], ++vaule);
            }
        }*/
        int a = -1;
        for (int i = 0; i < words.length; i++) {
            String keyword = words[i];
            Integer value = wordNum.get(keyword);
            if (value != null) {
                wordNum.put(keyword, ++value);
            } else {
                wordNum.put(keyword, 1);
                while (true) {
                    a = s.indexOf(keyword, a + 1);
                    if (a != -1) {
                        wordAtSIndex.put(a, words[i]);
                    } else {
                        break;
                    }
                }
            }
        }

        //起始下标不会大于这个值
        int last = s.length() - words.length * length;
        for (int i = 0; i <= last; i++) {
            if (wordAtSIndex.get(i) != null) {
                Map<String, Integer> map = new HashMap();
                for (int index = i, j = 0; j < words.length; j++, index += length) {
                    String value = wordAtSIndex.get(index);
                    if (value == null || (map.get(value) != null && map.get(value) >= wordNum.get(value))) {
                        break;
                    } else {
                        Integer num = map.get(value);
                        if (num != null) {
                            map.put(value, ++num);
                        } else {
                            map.put(value, 1);
                        }
                    }
                    if (j == words.length - 1) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 解题思路：这题咋一看好难，有种暴力法是把words所有组合可能列出来，然后一一对比，想着都耗时，肯定有更简便的方法
     *
     * 我们分析题意，words中的字符长度是一致的，这个比较关键，假设words的长度是1,是不是感觉会简单许多
     *
     * 这就变成字串中是否包含数组中所有字符，那我们就可以用移动窗口来查询窗口中是否包含所有字符
     *
     * 想到查询，那用Hash表是最方便快捷的，我们可以定义两个Hash表，一个用来保存窗口中出现的字符串，一个用来保存数组中的字符串
     *
     * Hash表key为字符串，value为出现的次数
     *
     * 窗口有两个指针left和right，分别表示窗口的开始和结束，设每个单词的长度为l，每次取right+l作为一个单词，将这个单词存入窗口Hash表中
     *
     * 假设这个单词在数组Hash表中不存在，说明这个区间不合格，所以需要将left置为right,right置为right+l继续找
     *
     * 假设这个单词在数组Hash表中存在并且出现次数小于数组Hash表出现次数，那么将right加上l继续取，否则将left加上l
     *
     * 当right-left的区间长度正好为数组中所有字符加起来的长度时，表示匹配上正确的字符了
     *
     */
    public List<Integer> result2(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        Map<String, Integer> wordsCount = generateCount(words);//将所有字符加入数组Hash表
        int length = words[0].length();
        for (int i = 0; i < length; ++i) {//错位循环，保证每种情况都遍历到
            Map<String, Integer> window = new HashMap<>();
            int left = i;
            int right = i;
            while (right <= s.length() - length && left <= s.length() - length * words.length) {
                String sub = s.substring(right, right + length);
                incr(window, sub);//取一个字符加入窗口Hash表
                if (!wordsCount.containsKey(sub)) {//如果这个字符在数组Hash表中不存在，就清理窗口并重置left和right
                    window.clear();
                    right += length;
                    left = right;
                    continue;
                }
                while (window.get(sub) > wordsCount.get(sub)) {//当窗口Hash中字符次数多于数组Hash字符次数时，left+l,交从窗口中移除最左边的字符
                    decr(window, s.substring(left, left + length));
                    left += length;
                }
                right += length;
                if (right - left == length * words.length) {//当窗口长度正好等于数组字符总长度时，表示匹配成功，加入结果中
                    result.add(left);
                }
            }
        }
        return result;
    }

    private Map<String, Integer> generateCount(String[] words) {
        Map<String, Integer> wordsCount = new HashMap<>();
        for (String word : words) {
            incr(wordsCount, word);
        }
        return wordsCount;
    }

    private void incr(Map<String, Integer> map, String key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    private void decr(Map<String, Integer> map, String key) {
        if (map.containsKey(key)) {
            Integer value = map.get(key);
            if (value <= 1) {
                map.remove(key);
            } else {
                map.put(key, value - 1);
            }
        }
    }

    /**
     * result3
     */
    Map<String, Integer> wordCount = new HashMap<>();
    List<Pair<String, Integer>> activeRun;
    Map<String, Integer> activeRunWordCount;
    List<Integer> results = new ArrayList<>();

    public List<Integer> result3(String s, String[] words) {
        if (words.length == 0 || s.length() < words.length * words[0].length()) {
            return Collections.emptyList();
        }

        int maxLen = words.length;

        // Calculate original word count
        for (int i = 0; i < maxLen; i++) {
            if (wordCount.get(words[i]) == null) {
                wordCount.put(words[i], 1);
            } else {
                wordCount.put(words[i], wordCount.get(words[i]) + 1);
            }
        }

        int window = words[0].length();
        activeRun = new ArrayList<>(maxLen);
        activeRunWordCount = new HashMap<>();

        // For every window
        for (int i = 0; i < window; i++) {
            for (int j = i; j < s.length() - window + 1; j += window) {
                String word = s.substring(j, j + window);
                if (wordCount.get(word) != null) {
                    addToActiveRun(word, j, maxLen);
                } else {
                    stopRun(maxLen);
                    activeRun = new ArrayList<>(maxLen);
                    activeRunWordCount = new HashMap<>();
                }
            }
            stopRun(maxLen);
            activeRun = new ArrayList<>(maxLen);
            activeRunWordCount = new HashMap<>();
        }

        return results;
    }

    public void addToActiveRun(String word, int index, int maxLen) {
        if (activeRun.size() != maxLen) {
            activeRun.add(new Pair<>(word, index));
        } else {
            stopRun(maxLen);

            // Remove first element and decrease active run word count for it
            String first = activeRun.get(0).getKey();
            if (activeRunWordCount.get(first) == 1) {
                activeRunWordCount.remove(first);
            } else {
                activeRunWordCount.put(first, activeRunWordCount.get(first) - 1);
            }
            activeRun.remove(0);

            activeRun.add(new Pair<>(word, index));
        }

        // Increase active run word count for new word
        if (activeRunWordCount.get(word) == null) {
            activeRunWordCount.put(word, 1);
        } else {
            activeRunWordCount.put(word, activeRunWordCount.get(word) + 1);
        }
    }

    public void stopRun(int maxLen) {
        if (activeRun.size() == maxLen) {
            // Run word count should be exactly same as original word count to include in result
            boolean complete = true;
            for (String key : activeRunWordCount.keySet()) {
                if (activeRunWordCount.get(key) != wordCount.get(key)) {
                    complete = false;
                    break;
                }
            }
            if (complete) {
                results.add(activeRun.get(0).getValue());
            }
        }
    }

    @Test
    public void findSubstring() {
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "good"};
        Object result = result1(s, words);
        System.out.println(result);
    }
}
