package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

/**
 * @date : 2019/04/26 16:43:35
 * @author: liangenmao
 */
public class AddBinary67 {
    public String result1(String a, String b) {

        int len;

        if (a.length() > b.length()) {
            len = a.length();
        } else {
            len = b.length();
        }

        int plus = 0;

        StringBuilder resultString = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int current = retrieveValue(a, i) + retrieveValue(b, i) + plus;

            if (current >= 2) {
                plus = 1;
                current -= 2;
            } else {
                plus = 0;
            }

            resultString.append(current);
        }

        if (plus == 1) {
            resultString.append(plus);
        }

        return resultString.reverse().toString();
    }

    public int retrieveValue(String a, int index) {
        if (index >= a.length()) {
            return 0;
        }

        return (a.charAt(a.length() - 1 - index) - '0');
    }

    @Test
    public void addBinary() {
        String a = "123";
        String b = "1234";
        Object result = result1(a, b);
        PrintUtils.print(result);
    }
}
