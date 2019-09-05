package oneThousandAndOneHundredToTwoHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

/**
 * @date : 2019/09/05 15:15:17
 * @author: liangenmao
 */
public class NumPrimeArrangements1175 {
    public int result1(int n) {
        int mod = 1000000007;
        //n=1,1;n=2,1;n=3,2
        if (n <= 3) {
            return (n + 1) / 2;
        }
        //2和3为质数
        int count = 2;
        //6x,6x+2,6x+3,6x+4必不为质数(x>=1);质数必为6x+1/6x+5(6x-1)
        for (int i = 5; i <= n; i += 6) {
            if (isPrimeNumber(i)) {
                count++;
            }
            if (i + 2 <= n) {
                if (isPrimeNumber(i + 2)) {
                    count++;
                }
            }
        }
        //计算阶层
        int low = count > (n - count) ? n - count : count;
        int high = count > (n - count) ? count : n - count;
        long result = 1;
        for (int i = 1; i <= low; i++) {
            result *= i;
            result %= mod;
        }
        result *= result;
        result %= mod;
        for (int i = low + 1; i <= high; i++) {
            result *= i;
            result %= mod;
        }
        return (int) result;
    }

    private boolean isPrimeNumber(int num) {
        //num必不是2和3的倍数
        int sqrt = (int) Math.sqrt(num);
        for (int i = 5; i <= sqrt; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }


    private long factorial(int num, int mod){
        int factorial = 1;
        for (int i = 1; i <= num; i++) {
            factorial *= i;
            factorial %= mod;
        }
        return factorial;
    }

    @Test
    public void numPrimeArrangements() {
        int n = 2;
        Object result = result1(n);
        PrintUtils.print(result);
    }
}
