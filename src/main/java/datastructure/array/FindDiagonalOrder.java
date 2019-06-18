package datastructure.array;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

/**
 * @date : 2019/06/14 14:20:25
 * @author: liangenmao
 */
public class FindDiagonalOrder {
    public int[] result1(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0){
            return new int[0];
        }
        //a,b分别为x,y行走方向
        int a = -1;
        int b = 1;
        //m,n分别为行数和列数
        int m = matrix.length;
        int n = matrix[0].length;
        int num = m * n;
        //x,y为当前点坐标
        int x = 0;
        int y = 0;
        int[] result = new int[num];
        result[0] = matrix[0][0];
        for (int i = 1; i < num; i++) {
            x += a;
            y += b;
            if (x < 0 || y == n) {
                if (y < n) {
                    x -= a;
                } else {
                    x -= a;
                    y -= b;
                    x += 1;
                }
                a = -a;
                b = -b;
            } else if (y < 0 || x == m) {
                if (x < m) {
                    y -= b;
                } else {
                    x -= a;
                    y -= b;
                    y += 1;
                }
                a = -a;
                b = -b;
            }
            result[i] = matrix[x][y];
        }
        return result;
    }

    @Test
    public void findDiagonalOrder() {
        String ints = "[\n" +
                " [ 1, 2, 3 ],\n" +
                " [ 4, 5, 6 ],\n" +
                " [ 7, 8, 9 ]\n" +
                "]";
        int[][] matrix = ParamUtils.getInts(ints);
        Object result = result1(matrix);
        PrintUtils.print(result);
    }
}
