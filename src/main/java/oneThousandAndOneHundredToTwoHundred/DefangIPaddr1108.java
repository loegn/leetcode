package oneThousandAndOneHundredToTwoHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

/**
 * @date : 2019/07/08 11:02:17
 * @author: liangenmao
 */
public class DefangIPaddr1108 {
    public String result1(String address) {
        StringBuilder result = new StringBuilder();
        String replace = "[.]";
        for (char c : address.toCharArray()) {
            if (c == '.'){
                result.append(replace);
            }else {
                result.append(c);
            }
        }
        return result.toString();
    }

    @Test
    public void defangIPaddr() {
        String address = "1.1.1.1";
        Object result = result1(address);
        PrintUtils.print(result);
    }
}
