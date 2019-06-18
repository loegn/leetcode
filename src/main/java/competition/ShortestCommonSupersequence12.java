package competition;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

/**
 * @date : 2019/06/18 15:09:13
 * @author: liangenmao
 */
public class ShortestCommonSupersequence12 {
    public String result1(String str1, String str2) {
        String result = LCS(str1, str2);
        int i1 = 0;
        int i2 = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length(); i++) {
            char value = result.charAt(i);
            while (str1.charAt(i1) != value) {
                sb.append(str1.charAt(i1++));
            }
            while (str2.charAt(i2) != value) {
                sb.append(str2.charAt(i2++));
            }
            i1++;
            i2++;
            sb.append(result.charAt(i));
        }
        for (int i = i1; i < str1.length(); i++) {
            sb.append(str1.charAt(i));
        }
        for (int i = i2; i < str2.length(); i++) {
            sb.append(str2.charAt(i));
        }
        return sb.toString();
    }

    public String LCS(String str1, String str2) {
        String[][] c = new String[str1.length() + 1][str2.length() + 1];
        for (int row = 0; row <= str1.length(); row++) {
            c[row][0] = "";
        }
        for (int column = 0; column <= str2.length(); column++) {
            c[0][column] = "";
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + str1.charAt(i - 1);
                } else if (c[i][j - 1].length() > c[i - 1][j].length()) {
                    c[i][j] = c[i][j - 1];
                } else {
                    c[i][j] = c[i - 1][j];
                }
            }
        }
        return c[str1.length()][str2.length()];
    }

    @Test
    public void shortestCommonSupersequence() {
        String str1 = "abac";
        String str2 = "cab";
        Object result = result1(str1, str2);
        PrintUtils.print(result);
    }
}
