package oneToHundred;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @date : 2019/03/12 17:07
 * @author: liangenmao
 */
public class LetterCombinationsOfAPhoneNumber {
    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    List<String> output = new ArrayList<String>();

    public void backtrack(String combination, String next_digits) {
        // if there is no more digits to check
        if (next_digits.length() == 0) {
            // the combination is done
            output.add(combination);
        }
        // if there are still digits to check
        else {
            // iterate over all letters which map
            // the next available digit
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                // append the current letter to the combination
                // and proceed to the next digits
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }

    public List<String> result1(String digits) {
        if (digits.length() != 0) {
            backtrack("", digits);
        }
        return output;
    }

    public List<String> result2(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        Map<String, String> map = new HashMap<>();
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");

        List<String> list = new ArrayList<>(4);

        for (int i = 0; i < digits.length(); i++) {
            String tempString = map.get(digits.substring(0, 1));
            if (!list.isEmpty()) {
                //初始化大小后运行速度反而更慢了
                List<String> tempList = new ArrayList<>(list.size() * tempString.length());
                for (int k = 0; k < list.size(); k++) {
                    for (int j = 0; j < tempString.length(); j++) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(list.get(k));
                        sb.append(tempString.charAt(j));
                        tempList.add(sb.toString());
                    }
                }
                list = tempList;
            } else {
                for (int j = 0; j < tempString.length(); j++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(tempString.charAt(j));
                    list.add(sb.toString());
                }
            }
        }
        return list;
    }

    @Test
    public void letterCombinations() {
        long start = System.currentTimeMillis();
        List<String> result = result1("2347859645672");
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        long start2 = System.currentTimeMillis();
        List<String> result2 = result2("23478596456723");
        long end2 = System.currentTimeMillis();
        System.out.println(end2 - start2);
    }
}
