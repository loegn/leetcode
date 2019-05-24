package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

/**
 * @date : 2019/04/24 15:25
 * @author: liangenmao
 */
public class SpiralMatrixII59 {
    public int[][] result1(int n) {
        int[][] result = new int[n][n];
        if (n <= 0) {
            return result;
        }
        int i = 0, j = 0, di = 0, dj = 1;
        int count = n * n;
        for (int value = 1; value <= count; value++) {
            result[i][j] = value;
            if (verify(i + di, j + dj, result)) {
                int temp = dj;
                dj = -di;
                di = temp;
            }
            i += di;
            j += dj;
        }
        return result;
    }

    private boolean verify(int i, int j, int[][] ints) {
        if (i < 0 || i == ints.length || j < 0 || j == ints.length) {
            return true;
        } else if (ints[i][j] != 0) {
            return true;
        }
        return false;
    }

    public int[][] result2(int n) {
        int[][] arr = new int[n][n];
        int c = 1, j = 0;
        while (c <= n * n) {

            for (int i = j; i < n - j; i++) {
                arr[j][i] = c++;
            }
            for (int i = j + 1; i < n - j; i++) {
                arr[i][n - j - 1] = c++;
            }
            for (int i = n - j - 2; i >= j; i--) {
                arr[n - j - 1][i] = c++;
            }
            for (int i = n -j - 2; i > j; i--) {
                arr[i][j] = c++;
            }

            j++;
        }

        return arr;
    }

    @Test
    public void generateMatrix() {
        int n = 3;
        Object result = result1(n);
        PrintUtils.print(result);
    }

}
