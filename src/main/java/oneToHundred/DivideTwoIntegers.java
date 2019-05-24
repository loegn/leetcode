package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/03/26 10:49
 * @author: liangenmao
 */
public class DivideTwoIntegers {
    /**
     * 超时
     */
    public int result1(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean positive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        dividend = dividend < 0 ? dividend : -dividend;
        divisor = divisor < 0 ? divisor : -divisor;
        int i = 0;
        for (int sum = 0; dividend - sum <= divisor; i++) {
            sum = sum + divisor;
        }
        return positive ? i : -i;
    }

    public int result2(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean positive = (dividend ^ divisor) >= 0;
        dividend = dividend <= 0 ? dividend : -dividend;
        divisor = divisor <= 0 ? divisor : -divisor;
        int a = 0;
        int b = 0;
        for (int i = 0; i < 31; i++) {
            int result = -1 << i;
            if (dividend <= result) {
                a = i;
            }
            if (divisor <= result) {
                b = i;
            }
        }
        a += 1;
        int max = a - b;
        int result = 0;
        int sum = 0;
        for (int j = max; j >= 0; j--) {
            //防止数值溢出
            if (sum + (divisor << j) < 0 && dividend <= sum + (divisor << j)){
                result += 1 << j;
                sum += divisor << j;
            }
        }
        return positive ? result : -result;
    }

    @Test
    public void divide() {
        int dividend = 1493139202;
        int divisor = 1493139203;
        Object result = result2(dividend, divisor);
        System.out.println(result);
    }

    @Test
    public void yichu(){
        int test = Integer.MIN_VALUE >> 1;
        test-=5;
        test = Integer.MIN_VALUE;
        int result = test << 2;
        System.out.println(result);
        System.out.println(Integer.MAX_VALUE - result);
    }
}
