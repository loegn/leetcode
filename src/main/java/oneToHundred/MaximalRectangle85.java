package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

import java.util.Arrays;

/**
 * @date : 2019/05/23 17:10:37
 * @author: liangenmao
 */
public class MaximalRectangle85 {
    /**
     * 扩张法 从左上到右下遍历每个点求出其向右下方向能扩张得最大面积
     *
     * @see <a href="https://leetcode.com/problems/maximal-rectangle/discuss/225690/Java-solution-with-explanations-in-Chinese"></a>
     * <p>
     * 所求矩形的第一个坐标点必然是这个二维数组中的某一点，且这一点是'1'
     * 所求矩形一定是从一个 1*1 的矩形逐渐扩张而来的
     * 所以，我们可以计算从二维数组中的每个点扩张而成的矩形的面积的最大值，即为待求解。
     * 因为已经假定是从某一个点扩张，所以它只能选择向右扩张或向下扩张，然后在每一个递归函数中判断是否能够完成扩张：
     */
    private static int max = 0;

    public static int result1(char[][] matrix) {
        max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    max = Math.max(max, 1);
                    max(matrix, i, j, 1, 1);
                }
            }
        }
        return max;
    }

    public static void max(char[][] matrix, int i, int j, int w, int h) {
        //right
        if (j + w < matrix[0].length) {
            for (int k = 0; k < h; k++) {
                if (matrix[i + k][j + w] == '0') {
                    break;
                }
                if (k == h - 1) {
                    max = Math.max(max, h * (w + 1));
                    max(matrix, i, j, w + 1, h);
                }
            }
        }
        //down
        if (i + h < matrix.length) {
            for (int k = 0; k < w; k++) {
                if (matrix[i + h][j + k] == '0') {
                    break;
                }
                if (k == w - 1) {
                    max = Math.max(max, (h + 1) * w);
                    max(matrix, i, j, w, h + 1);
                }
            }
        }
    }

    /**
     * 将每一行连续的'1'的个数标志出来，那么所有的横向的矩形就解出来了。
     * 再求以每一列为底的最大面积
     *
     * @see LargestRectangleInHistogram84
     */
    public static int result2(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    matrix[i][j] = (char) (matrix[i][j - 1] + 1);
                } else {
                    matrix[i][j] = '0';
                }
            }
        }
        int max = 0;
        /**
         * 以下代码可以用单调栈求解来优化
         * @see LargestRectangleInHistogram84#result3(int[])
         */
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int min = matrix[i][j] - '0';
                if (min > 0) {
                    max = Math.max(max, min);
                    for (int k = i - 1; k >= 0 && matrix[k][j] != '0'; k--) {
                        min = Math.min(min, matrix[k][j] - '0');
                        max = Math.max(max, min * (i - k + 1));
                    }
                }
            }
        }
        return max;
    }

    /**
     * 按行求解
     */
    public static int result3(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            max = Math.max(max, largest(heights, 0, n - 1));
        }
        return max;
    }

    /**
     * @see LargestRectangleInHistogram84#result5(int[])
     */
    public static int largest(int[] heights, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return heights[start];
        }
        boolean sorted = true;
        int min = start;
        for (int i = start + 1; i <= end; i++) {
            if (heights[i] < heights[i - 1]) {
                sorted = false;
            }
            if (heights[i] < heights[min]) {
                min = i;
            }
        }
        if (sorted) {
            int max = heights[start] * (end - start + 1);
            for (int i = start + 1; i <= end; i++) {
                max = Math.max(max, heights[i] * (end - i + 1));
            }
            return max;
        }
        return Math.max(Math.max(largest(heights, start, min - 1), largest(heights, min + 1, end)),
                heights[min] * (end - start + 1));
    }

    /**
     * 再比如下面的使用最宽长度法求解：
     */

    public static int result4(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[] left = new int[n], right = new int[n], height = new int[n];
        Arrays.fill(right, n);
        int max = 0;
        for (int i = 0; i < m; i++) {
            int curLeft = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    height[j] = 0;
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            int curRight = n;
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = n;
                    curRight = j;
                }
            }
            for (int j = 0; j < n; j++) {
                max = Math.max(max, (right[j] - left[j]) * height[j]);
            }
        }
        return max;
    }

    @Test
    public void maximalRectangle() {
        String chars = "[\n" +
                "  [\"1\",\"0\",\"1\",\"0\",\"0\"],\n" +
                "  [\"1\",\"0\",\"1\",\"1\",\"1\"],\n" +
                "  [\"1\",\"1\",\"1\",\"1\",\"1\"],\n" +
                "  [\"1\",\"0\",\"0\",\"1\",\"0\"]\n" +
                "]";
        char[][] matrix = ParamUtils.getChars(chars);
        Object result = result1(matrix);
        PrintUtils.print(result);
    }
}
