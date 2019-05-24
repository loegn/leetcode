package oneToHundred;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @date : 2019/03/25 10:29
 * @author: liangenmao
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        char left = '(';
        char right = ')';
        List<String> list = new ArrayList<>();
        list.add("");
        for (int i = 1; i <= n; i++) {
            List<String> tempList = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                String s = list.get(j);
                s += left;
                int maxNum = 2 * i - s.length();
                if (i == n) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(s);
                    for (int j1 = 0; j1 < maxNum; j1++) {
                        stringBuilder.append(right);
                    }
                    tempList.add(stringBuilder.toString());
                } else {
                    for (int k = 0; k <= maxNum; k++) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(s);
                        for (int k1 = 0; k1 < k; k1++) {
                            stringBuilder.append(right);
                        }
                        tempList.add(stringBuilder.toString());
                    }
                }
            }
            list = tempList;
        }
        return list;
    }

    private List<String> result2(int n){
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left: generateParenthesis(c)) {
                    for (String right: generateParenthesis(n-1-c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int n = 3 ;
        List<String> result = result2(n);
        String resultStr = JSON.toJSONString(result);
        System.out.println(resultStr);
        System.out.println(result.size());
    }
}
