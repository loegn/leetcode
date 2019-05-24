package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @date : 2019/05/23 14:10:28
 * @author: liangenmao
 */
public class LargestRectangleInHistogram84 {
    /**
     * 时间复杂度比较高 600+ms O(n^2)
     */
    public int result1(int[] heights) {
        int leftIndex = 0;
        int rightIndex = heights.length - 1;
        int vol = helper(heights, leftIndex, rightIndex);
        return vol;
    }

    /**
     * 找出两个下标之间最低的高度
     */
    private int helper(int[] heights, int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex) {
            return 0;
        }
        int miniIndex = leftIndex;
        for (int i = leftIndex; i <= rightIndex; i++) {
            if (heights[i] < heights[miniIndex]) {
                miniIndex = i;
            }
        }
        int vol = heights[miniIndex] * (rightIndex - leftIndex + 1);
        vol = Math.max(vol, helper(heights, leftIndex, miniIndex - 1));
        vol = Math.max(vol, helper(heights, miniIndex + 1, rightIndex));
        return vol;
    }

    /**
     * 单调栈 37ms O(n)
     */
    public int result2(int[] heights) {
        int[] ints = new int[heights.length + 1];
        System.arraycopy(heights, 0, ints, 0, heights.length);
        ints[heights.length] = 0;
        heights = ints;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                maxArea = Math.max(maxArea, heights[stack.pop()] * (stack.isEmpty() ? i : i - (stack.peek() + 1)));
            }
            stack.push(i);
        }
        return maxArea;
    }

    /**
     * 单调栈 数据结构优化 19ms
     */
    public int result3(int[] heights) {
        LinkedList<Integer> list = new LinkedList<>();
        int maxArea = 0;
        for (int i = 0; i <= heights.length; i++) {
            while (!list.isEmpty() && (i == heights.length || heights[i] < heights[list.getLast()])) {
                maxArea = Math.max(maxArea, heights[list.removeLast()] * (list.isEmpty() ? i : i - (list.getLast() + 1)));
            }
            list.add(i);
        }
        return maxArea;
    }

    public int result4(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[heights.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[heights.length]; // idx of the first bar the right that is lower than current
        lessFromRight[heights.length - 1] = heights.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < heights.length; i++) {
            int p = i - 1;

            while (p >= 0 && heights[p] >= heights[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = heights.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < heights.length && heights[p] >= heights[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }

    /**
     * 分治递归优化 3ms
     * 这么快的时间应该是测试用例不全面导致的
     */
    public int result5(int[] heights) {
        int len = heights.length;
        if (len == 0)
            return 0;
        return maxArea(heights, 0, len - 1);
    }

    private int maxArea(int[] h, int left, int right) {
        if (left == right)
            return h[right];
        int min = left;
        boolean sorted = true;
        for (int i = left + 1; i <= right; i++) {
            if (h[i] < h[i - 1])
                sorted = false;
            if (h[i] < h[min])
                min = i;
        }
        if (sorted) {
            int max = 0;
            for (int i = left; i <= right; i++)
                max = Math.max(max, h[i] * (right - i + 1));
            return max;
        }
        int l = (min > left) ? maxArea(h, left, min - 1) : 0;
        int r = (min < right) ? maxArea(h, min + 1, right) : 0;
        return Math.max(Math.max(l, r), h[min] * (right - left + 1));
    }

    @Test
    public void largestRectangleArea() {
        String ints = "[2,1,5,6,2,3]";
        int[] heights = ParamUtils.getInt(ints);
        Object result = result2(heights);
        PrintUtils.print(result);
    }
}
