package oneToHundred;

import java.util.ArrayList;
import java.util.List;

/**
 * @date : 2019/02/20 14:40
 * @author: liangenmao
 */
public class ZigZagConversion {
    public static void main(String[] args) {
        String result = result1("LEETCODEISHIRING", 3);
        System.out.println(result);
        System.out.println("LCIRETOESIIGEDHN".equals(result));
    }

    public static String result1(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        char[] result = new char[s.length()];
        int rowNum = (numRows - 1) * 2;
        int index = 0;
        while (index < result.length) {
            for (int r0 = 0; r0 < result.length; ) {
                result[index] = s.charAt(r0);
                index++;
                r0 += rowNum;
            }
            for (int i = 1; i < numRows - 1; i++) {
                int ri = -i;
                while (true) {
                    ri += 2 * i;
                    if (ri < result.length) {
                        result[index] = s.charAt(ri);
                        index++;
                    } else {
                        break;
                    }
                    ri += rowNum - 2 * i;
                    if (ri < result.length) {
                        result[index] = s.charAt(ri);
                        index++;
                    } else {
                        break;
                    }
                }
            }
            for (int rn = numRows - 1; rn < result.length; ) {
                result[index] = s.charAt(rn);
                index++;
                rn += rowNum;
            }
        }
        return new String(result);
    }

    public static String result2(String s, int numRows){

        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }
        return ret.toString();
    }

    public String result3(String s, int numRows) {

        if (numRows == 1) {
            return s;
        }

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    ret.append(s.charAt(j + cycleLen - i));
                }
            }
        }
        return ret.toString();
    }

}
