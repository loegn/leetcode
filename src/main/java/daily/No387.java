package daily;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 */
public class No387 {
    /**
     * 使用哈希表存储频数
     */
    public int firstUniqChar1(String s) {
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (frequency.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 方法二：使用哈希表存储索引
     *
     * @param s
     * @return
     */
    public int firstUniqChar2(String s) {
        Map<Character, Integer> position = new HashMap<Character, Integer>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if (position.containsKey(ch)) {
                position.put(ch, -1);
            } else {
                position.put(ch, i);
            }
        }
        int first = n;
        for (Map.Entry<Character, Integer> entry : position.entrySet()) {
            int pos = entry.getValue();
            if (pos != -1 && pos < first) {
                first = pos;
            }
        }
        if (first == n) {
            first = -1;
        }
        return first;
    }

    /**
     * 队列
     * @param s
     * @return
     */
    public int firstUniqChar3(String s) {
        Map<Character, Integer> position = new HashMap<Character, Integer>();
        Queue<Pair> queue = new LinkedList<Pair>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if (!position.containsKey(ch)) {
                position.put(ch, i);
                queue.offer(new Pair(ch, i));
            } else {
                position.put(ch, -1);
                while (!queue.isEmpty() && position.get(queue.peek().ch) == -1) {
                    queue.poll();
                }
            }
        }
        return queue.isEmpty() ? -1 : queue.poll().pos;
    }

    class Pair {
        char ch;
        int pos;

        Pair(char ch, int pos) {
            this.ch = ch;
            this.pos = pos;
        }
    }

    /**
     * 我的解法
     *
     * @param s
     * @return
     */
    public int firstUniqChar4(String s) {
        int[] count = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < length; i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
