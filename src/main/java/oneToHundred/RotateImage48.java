package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/04/16 16:23
 * @author: liangenmao
 */
public class RotateImage48 {
    public void result1(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len / 2; i++) {
            int start = i;
            int end = len - i - 1;
            for (int j = 0; j < end - start; j++) {
                int temp = matrix[start][start + j];
                matrix[start][start + j] = matrix[end - j][start];
                matrix[end - j][start] = matrix[end][end - j];
                matrix[end][end - j] = matrix[start + j][end];
                matrix[start + j][end] = temp;
            }
        }
    }

    /**
     * 先把纵向翻转，即变成 [1, 4, 7] [2, 5, 8] [3, 6, 9]， 然后把每层数组反转即可
     */
    public void result2(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
            for (int j = 0; j < (len >> 1); j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][len - 1 - j];
                matrix[i][len - 1 - j] = temp;
            }
        }
    }

    @Test
    public void rotate() {
        int[][] nums = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        result1(nums);
        System.out.println(nums);
    }
}
