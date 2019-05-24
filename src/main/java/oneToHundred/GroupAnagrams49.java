package oneToHundred;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @date : 2019/04/16 17:14
 * @author: liangenmao
 */
public class GroupAnagrams49 {
    public List<List<String>> result1(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<C, List<String>> map = new HashMap<>();
        for (String str : strs) {
            C c = new C(str);
            if (!map.containsKey(c)) {
                List<String> list = new ArrayList<>();
                map.put(c, list);
                result.add(list);
            }
            map.get(c).add(str);
        }
        return result;
    }

    private class C {
        private char[] chars;

        private C(String s) {
            this.chars = s.toCharArray();
            Arrays.sort(chars);
        }

        public char[] getChars() {
            return chars;
        }

        @Override
        public int hashCode() {
            int hashCode = 1;
            for (char c : chars) {
                hashCode = 31 * hashCode + c;
            }
            return hashCode;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C)) {
                return false;
            }
            char[] c1 = this.chars;
            char[] c2 = ((C) obj).getChars();
            if (c1.length != c2.length) {
                return false;
            }
            for (int i = 0; i < c1.length; i++) {
                if (c1[i] != c2[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    public List<List<String>> result2(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList();
        }
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    @Test
    public void groupAnagrams() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Object result = result1(strs);
        System.out.println(result);
    }
}
