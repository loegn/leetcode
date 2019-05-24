package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

/**
 * @date : 2019/04/29 10:42:50
 * @author: liangenmao
 */
public class SearchA2DMatrix74 {

    /**
     * 二分法（递归实现,O(log(m*n))）
     */
    public boolean result1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        return helper(matrix, target, 0, matrix.length * matrix[0].length - 1);
    }

    private boolean helper(int[][] matrix, int target, int start, int end) {
        if (start > end) {
            return false;
        }
        int mid = (end - start >> 1) + start;
        int midValue = matrix[mid / matrix[0].length][mid % matrix[0].length];
        if (midValue == target) {
            return true;
        } else if (midValue > target) {
            return helper(matrix, target, 0, mid - 1);
        } else {
            return helper(matrix, target, mid + 1, end);
        }
    }

    /**
     * 二分法（循环实现,O(log(m*n))）
     */
    public boolean result2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length - 1;
        int left = 0, right = row;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (matrix[mid][0] < target) {
                left = mid + 1;
            } else if (matrix[mid][0] > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        row = Math.max(Math.min(right, left), 0);
        left = 0;
        right = matrix[row].length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (matrix[row][mid] > target) {
                right = mid - 1;
            } else if (matrix[row][mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 右上角向左下角寻找
     * O(m+n)
     */
    public boolean result3(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] < target) {
                row++;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                return true;
            }
        }
        return false;
    }

    @Test
    public void searchMatrix() {
        String ints = "[]";
        int[][] matrix = ParamUtils.getInts(ints);
        int target = 3;
        Object result = result1(matrix, target);
        PrintUtils.print(result);
    }
}
