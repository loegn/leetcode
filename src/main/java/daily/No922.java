package daily;

import utils.PrintUtils;

/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 */
public class No922 {
    public int[] sortArrayByParityII1(int[] A) {
        int n = A.length;
        int[] ans = new int[n];

        int i = 0;
        for (int x : A) {
            if (x % 2 == 0) {
                ans[i] = x;
                i += 2;
            }
        }
        i = 1;
        for (int x : A) {
            if (x % 2 == 1) {
                ans[i] = x;
                i += 2;
            }
        }
        return ans;
    }

    public int[] sortArrayByParityII2(int[] A) {
        int n = A.length;
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                swap(A, i, j);
            }
        }
        return A;
    }

    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public int[] sortArrayByParityII3(int[] A) {
        int length = A.length;
        int[] odd = new int[length / 2];
        int[] even = new int[length / 2];
        int oddIndex = 0;
        int evenIndex = 0;
        for (int i = 0; i < A.length; i++) {
            int value = A[i];
            if ((value & 1) == 1) {
                odd[oddIndex++] = value;
            } else {
                even[evenIndex++] = value;
            }
        }
        int[] result = new int[length];
        for (int i = 0; i < length / 2; i++) {
            result[2 * i] = even[i];
            result[2 * i + 1] = odd[i];
        }
        return result;
    }

    public static void main(String[] args) {
        No922 no922 = new No922();
        int[] input = {4,2,5,7};
        int[] result = no922.sortArrayByParityII2(input);
        PrintUtils.print(result);
    }
}
