package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * @date : 2019/04/28 15:54:15
 * @author: liangenmao
 */
public class SimplifyPath71 {
    /**
     * 错误
     */
    public String result1(String path) {
        PrintUtils.error();
        if ("/".equals(path)) {
            return "/";
        }
        List<String> splits = new ArrayList<>();
        for (int i = 0; i < path.length(); i++) {
            while (path.charAt(++i) == '/') {
                if (i == path.length() - 1) {
                    return "/";
                }
            }
            int start = i;
            for (; i < path.length(); i++) {
                if (path.charAt(i) == '/') {
                    splits.add(path.substring(start, i));
                } else if (i == path.length() - 1) {
                    splits.add(path.substring(start));
                }
            }
        }
        int index = 0;
//        String[] result = new String[strings.length];
        for (int i = 0; i < splits.size(); i++) {
            if (Objects.equals(splits.get(i), ".") || Objects.equals(splits.get(i), "")) {

            } else if (Objects.equals(splits.get(i), "..")) {
                index = index == 0 ? 0 : index - 1;
            } else {
                splits.set(index, splits.get(i));
                index++;
            }
        }
        StringBuilder sb = new StringBuilder();
        char separator = '/';
        for (int i = 0; i < index; i++) {
            sb.append(separator);
            sb.append(splits.get(i));
        }
        return sb.length() > 0 ? sb.toString() : "/";
    }

    public String result2(String path) {
        Stack<String> s = new Stack();
        int j = 0, len = path.length();
        boolean flag = true;
        for (int i = 0; i <= len; i++) {
            if (i != len && path.charAt(i) != '/') {
                if (flag) {
                    flag = false;
                    j = i;
                }
            } else {
                if (!flag) {
                    flag = true;
                    String tmp = path.substring(j, i);
                    if (tmp.equals(".")) {
                        continue;
                    } else if (tmp.equals("..")) {
                        if (!s.isEmpty()) {
                            s.pop();
                        }
                    } else {
                        s.push(tmp);
                    }
                }
            }
        }
        if (s.isEmpty()) {
            return "/";
        }
        String res = "";
        while (!s.isEmpty()) {
            res = "/" + s.pop() + res;
        }
        return res;
    }

    @Test
    public void simplifyPath() {
        String path = "/..//";
        Object result = result1(path);
        PrintUtils.print(result);
    }
}
