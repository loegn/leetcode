package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/04/12 09:51
 * @author: liangenmao
 */
public class TrappingRainWater42 {
    public int result1(int[] height) {
        int leftHeight = 0;
        int vol = 0;
        //从左往右计算容量
        for (int i = 0; i < height.length; i++) {
            if (height[i] < leftHeight) {
                vol += leftHeight - height[i];
            } else {
                leftHeight = height[i];
            }
        }
        //去除多计算的容量
        int rightHeight = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] == leftHeight) {
                break;
            }
            if (height[i] > rightHeight) {
                vol -= leftHeight - height[i];
                rightHeight = height[i];
            } else {
                vol -= leftHeight - rightHeight;
            }
        }
        return vol;
    }

    public int result2(int[] height) {
        int heightestIndex = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > height[heightestIndex]) {
                heightestIndex = i;
            }
        }
        int vol = 0;
        // TODO        从左边向最高点遍历
        // TODO       从右边向最高点遍历
        return vol;
    }

    @Test
    public void trap() {
        int[] candidates = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Object result = result1(candidates);
        System.out.println(result);
    }
}
