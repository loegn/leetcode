package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

/**
 * @date : 2019/04/28 13:29:41
 * @author: liangenmao
 */
public class SqrtX69 {
    /**
     * 超时
     */
    public int result1(int x) {
        if (x == 0) {
            return x;
        }
        int result = sqrt(x, 0);
        return result;
    }

    private int sqrt(int x, int basic) {
        int curr = 1;
        int value = basic + curr;
        while (value * value <= x) {
            curr <<= 1;
            value = curr + basic;
        }
        if (curr == 1) {
            return basic;
        } else {
            return sqrt(x, basic + (curr >> 1));
        }
    }

    /**
     *
     */
    public int result2(int x) {
        //最大值为46340
        return sqrt2(x, 0, x > 1 << 15 ? 1 << 15 : x);
    }

    /**
     * [basic,basic+end)
     */
    private int sqrt2(int x, int basic, int end) {
        if (end == 0) {
            return basic;
        }
        //可能会溢出
        while ((basic + end) * (basic + end) > x || (basic + end) * (basic + end) <= 0) {
            end >>= 1;
        }
        return sqrt2(x, basic + end, end);
    }

    /**
     * 二分法
     */
    public int result3(int x) {
        if (x < 0) {
            return -1;
        }
        if (x == 0) {
            return 0;
        }
//        if(x> 46340*46340) {
//            return 46340;
//        }
        int left = 1, right = 46340;//46340
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if ((mid * mid) == x) {
                return mid;
            } else if (mid * mid < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return mid * mid < x ? mid : mid - 1;
    }

    /**
     * 牛顿法
     *
     * @see <a href = "https://en.wikipedia.org/wiki/Integer_square_root#Using_only_integer_division"></a>
     */
    public int result4(int x) {
        if (x == 0) {
            return 0;
        }
        double cur = x;
        double last = 0;
        while (Math.abs(cur - last) >= 1) {
            last = cur;
            cur = (cur * cur + x) / (2 * cur);
        }
        return (int) cur;
    }

    @Test
    public void mySqrt() {
        int x = Integer.MAX_VALUE;
        Object result = result2(x);
        PrintUtils.print(result);
    }
}
