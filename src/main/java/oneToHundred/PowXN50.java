package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/04/17 11:45
 * @author: liangenmao
 */
public class PowXN50 {
    public double result1(double x, int n) {
        double res = 1.0;
        for (int i = n; i != 0; i /= 2) {
            if ((i & 1) == 1) {
                res *= x;
            }
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }

    @Test
    public void myPow() {
        double x = 2.00000D;
        int n = 3;
        Object result = result1(x, n);
        System.out.println(result);
    }
}
