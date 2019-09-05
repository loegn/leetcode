package oneThousandAndOneHundredToTwoHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

/**
 * @date : 2019/09/05 16:47:56
 * @author: liangenmao
 */
public class DietPlanPerformance1176 {
    public int result1(int[] calories, int k, int lower, int upper) {
        int sum = 0;
        int result = 0;
        for (int i = 0; i < calories.length; i++) {
            sum += calories[i];
            if (i >= k - 1) {
                if (sum < lower) {
                    result--;
                }
                if (sum > upper) {
                    result++;
                }
                sum -= calories[i - k + 1];
            }
        }
        return result;
    }

    @Test
    public void dietPlanPerformance() {
        String ints = "[3,2]";
        int[] calories = ParamUtils.getInt(ints);
        int k = 2;
        int lower = 0;
        int upper = 1;
        Object result = result1(calories, k, lower, upper);
        PrintUtils.print(result);
    }
}
