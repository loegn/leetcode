package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

import java.util.Arrays;

/**
 * @date : 2019/04/29 09:25:06
 * @author: liangenmao
 */
public class SetMatrixZeroes73 {
    /**
     * O(mn)空间
     */
    public void result1(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int[][] copy = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                if (copy[i][j] == 0) {
                    for (int i1 = 0; i1 < matrix.length; i1++) {
                        matrix[i1][j] = 0;
                    }
                    for (int j1 = 0; j1 < matrix[i].length; j1++) {
                        matrix[i][j1] = 0;
                    }
                }
            }
        }
    }

    /**
     * O(m+n)空间
     */
    public void result2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        boolean[] m = new boolean[matrix.length];
        boolean[] n = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    m[i] = true;
                    n[j] = true;
                }
            }
        }
        for (int i = 0; i < m.length; i++) {
            if (m[i]){
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < n.length; j++) {
            if (n[j]){
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * O(1)空间
     */
    public void result3(int[][] matrix) {
        boolean row = false;
        boolean col = false;
        //判断首行首列是否要置0
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0){
                col = true;
                break;
            }
        }
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0){
                row = true;
                break;
            }
        }
        //把要改变为0的行列记录到首行首列
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        //改变行列为0
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0){
                for (int j = 1; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0){
                for (int i = 1; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        //把首行首列改变为0
        if (row){
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col){
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    @Test
    public void setZeroes() {
        String ints = "\n" +
                "[[0,1,2,0],[3,4,5,2],[1,3,1,5]]";
        int[][] matrix = ParamUtils.getInts(ints);
        result3(matrix);
        PrintUtils.print(matrix);
    }
}
