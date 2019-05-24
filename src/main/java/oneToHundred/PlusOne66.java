package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

/**
 * @date : 2019/04/26 16:20:03
 * @author: liangenmao
 */
public class PlusOne66 {
    public int[] result1(int[] digits) {
        boolean carry = true;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (carry) {
                if (digits[i] == 9) {
                    digits[i] = 0;
                } else {
                    digits[i] += 1;
                    carry = false;
                }
            } else {
                break;
            }
        }
        if (carry){
            int[] result = new int[digits.length+1];
            System.arraycopy(digits,0,result,1,digits.length);
            result[0] = 1;
            return result;
        }else {
            return digits;
        }
    }

    @Test
    public void plusOne() {
        int[] digits = {9};
        Object result = result1(digits);
        PrintUtils.print(result);
    }
}
