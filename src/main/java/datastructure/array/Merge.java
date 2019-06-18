package datastructure.array;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

/**
 * @date : 2019/06/14 11:14:38
 * @author: liangenmao
 */
public class Merge {
    /**
     * 1、copy一份nums1数组
     * 2、填充后排序
     */
    public void result1(int[] nums1, int m, int[] nums2, int n) {
    }

    /**
     * 从后往前
     */
    public void result2(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        int i1 = m - 1;
        int i2 = n - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            if (nums1[i1] > nums2[i2]) {
                nums1[i] = nums1[i1];
                if (i1 == 0) {
                    System.arraycopy(nums2, 0, nums1, 0, i2 + 1);
                    break;
                }
                i1--;
            } else {
                nums1[i] = nums2[i2];
                if (i2 == 0) {
                    break;
                }
                i2--;
            }
        }
    }

    @Test
    public void merge() {
        String ints1 = "[0]";
        String ints2 = "[1]";
        int[] nums1 = ParamUtils.getInt(ints1);
        int m = 0;
        int[] nums2 = ParamUtils.getInt(ints2);
        int n = 1;
        result2(nums1, m, nums2, n);
        PrintUtils.print(nums1);
    }
}
