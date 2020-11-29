package daily;

import utils.ParamUtils;
import utils.PrintUtils;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/largest-perimeter-triangle/
 * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 * 如果不能形成任何面积不为零的三角形，返回 0。
 */
public class No976 {
    /**
     * 贪心+排序
     * @param A
     * @return
     */
    public int largestPerimeter1(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; --i) {
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        return 0;
    }

    public int largestPerimeter2(int[] A) {
        Arrays.sort(A);
        int max = 0;
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i - 1] + A[i - 2] > A[i]) {
                max = Math.max(max,A[i - 1] + A[i - 2] + A[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        No976 no976 = new No976();
        int[] input = ParamUtils.getInt("[3,2,3,4]");
        Object result = no976.largestPerimeter1(input);
        PrintUtils.print(result);
    }
}
