package daily;

import java.util.Arrays;

public class No977 {
    public int[] sortedSquares1(int[] A) {
        int n = A.length;
        int negative = -1;
        for (int i = 0; i < n; ++i) {
            if (A[i] < 0) {
                negative = i;
            } else {
                break;
            }
        }

        int[] ans = new int[n];
        int index = 0, i = negative, j = negative + 1;
        while (i >= 0 || j < n) {
            if (i < 0) {
                ans[index] = A[j] * A[j];
                ++j;
            } else if (j == n) {
                ans[index] = A[i] * A[i];
                --i;
            } else if (A[i] * A[i] < A[j] * A[j]) {
                ans[index] = A[i] * A[i];
                --i;
            } else {
                ans[index] = A[j] * A[j];
                ++j;
            }
            ++index;
        }
        return ans;
    }

    public int[] sortedSquares2(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) {
                A[i] = -A[i];
            }
        }
        Arrays.sort(A);
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            result[i] = A[i] * A[i];
        }
        return result;
    }

    public int[] sortedSquares3(int[] A) {
        int length = A.length;
        int[] result = new int[length];
        /** 小于这个需要改变排序 */
        int separate = 0;

        int negative = separate;
        int postive = separate;
        while (negative >= 0 & postive < length) {

        }
        return result;
    }
}
