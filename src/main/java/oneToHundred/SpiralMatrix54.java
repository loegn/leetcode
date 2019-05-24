package oneToHundred;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date : 2019/04/18 15:26
 * @author: liangenmao
 */
public class SpiralMatrix54 {
    /**
     * 递归实现
     */
    public List<Integer> result1(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix != null && matrix.length != 0 && matrix[0] != null && matrix[0].length != 0) {
            helper(list, matrix, 0, matrix[0].length - 1, 0, matrix.length - 1);
        }
        return list;
    }

    private void helper(List<Integer> list, int[][] matrix, int colStart, int colEnd, int rowStart, int rowEnd) {
        int col = colEnd - colStart;
        int row = rowEnd - rowStart;
        if (col >= 1 && row >= 1) {
            //right
            for (int i = colStart; i < colEnd; i++) {
                list.add(matrix[rowStart][i]);
            }
            //down
            for (int i = rowStart; i < rowEnd; i++) {
                list.add(matrix[i][colEnd]);
            }
            //left
            for (int i = colEnd; i > colStart; i--) {
                list.add(matrix[rowEnd][i]);
            }
            //up
            for (int i = rowEnd; i > rowStart; i--) {
                list.add(matrix[i][colStart]);
            }
        } else if (col == 0) {
            //down
            for (int i = rowStart; i <= rowEnd; i++) {
                list.add(matrix[i][colStart]);
            }
        } else if (row == 0) {
            //right
            for (int i = colStart; i <= colEnd; i++) {
                list.add(matrix[rowStart][i]);
            }
        } else {
            return;
        }
        helper(list, matrix, colStart + 1, colEnd - 1, rowStart + 1, rowEnd - 1);
    }

    /**
     * 转向
     */
    public List<Integer> result2(int[][] matrix) {
        List<Integer> r = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return r;
        }
        boolean[][] visited = new boolean[matrix.length ][matrix[0].length];
        int i = 0, j = 0, di = 0, dj = 1;
        for (int count = 0; count < matrix.length * matrix[0].length; count++) {
            r.add(matrix[i][j]);
            visited[i][j] = true;
//            if (visited[(i + di) % visited.length][(j + dj) % visited[0].length]) {
            if (get(visited, i + di, j + dj)) {
                //0,1->1，0->0，-1->-1，0->0,1
                int temp = di;
                di = dj;
                dj = -temp;
            }
            i += di;
            j += dj;
        }
        return r;

//        python:
//        r, i, j, di, dj = [], 0, 0, 0, 1
//        for _ in range(len(matrix) * len(matrix[0])):
//        r.append(matrix[i][j])
//        matrix[i][j] = 0
//        if matrix[(i + di) % len(matrix)][(j + dj) % len(matrix[0])] == 0:
//        di, dj = dj, -di
//        i += di
//        j += dj
//        return r
    }

    private boolean get(boolean[][] visited, int i, int j) {
        if (i < 0) {
            i = 0;
        } else if (i >= visited.length) {
            i = 0;
        }
        if (j < 0) {
            j = 0;
        } else if (j >= visited[0].length) {
            j = 0;
        }
        return visited[i][j];
    }

    @Test
    public void spiralOrder() {
//        int matrix[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int matrix[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int matrix[][] = {{3}, {2}};

        Object result = result2(matrix);
        print(result);
    }

    private void print(Object result) {
        System.out.println(result);
    }
}
