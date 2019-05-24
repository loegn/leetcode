package oneToHundred;

/**
 * @date : 2019/02/27 16:00
 * @author: liangenmao
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strings = {"flower", "flow", "flight"};
        String result = longestCommonPrefix(strings);
        System.out.println(result);
        "".indexOf("");
        "".isEmpty();
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Boolean breakFlag = false;
        int i = 0;
        for (; i < strs[0].length(); i++) {
            Character c = null;
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].length() >= i) {
                    if (c != null) {
                        if (strs[j].charAt(i) != c) {
                            breakFlag = true;
                        }
                    } else {
                        c = strs[j].charAt(i);
                    }
                } else {
                    breakFlag = true;
                }
            }
            if (breakFlag) {
                break;
            }
        }
        return strs[0].substring(0, i);
    }
}
