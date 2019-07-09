package oneThousandAndOneHundredToTwoHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

/**
 * @date : 2019/07/08 11:09:04
 * @author: liangenmao
 */
public class CorpFlightBookings1109 {
    /**
     * 超时
     */
    public int[] result1(int[][] bookings, int n) {
        int[] result = new int[n];
        for (int[] booking : bookings) {
            for (int i = booking[0]; i <= booking[1]; i++) {
                result[i - 1] += booking[2];
            }
        }
        return result;
    }

    /**
     * 差分法
     */
    public int[] result2(int[][] bookings, int n) {
        //定义一个差分数组tag[]，tag[i]表示第i个航班与第i-1个航班预订座位的差，即tag[i] = a[i] - a[i - 1],最大下标为n+1。
        int[] tag = new int[n + 2];
        //定义tag数组并计算预订表的长度
        int len = bookings.length;
        //定义一个长度为n且值为0的动态数组
        int[] a = new int[n];
        //扫描每一个预订记录
        for (int i = 0; i < len; i++) {
            //对tag数组进行操作
            tag[bookings[i][0]] += bookings[i][2];
            tag[bookings[i][1] + 1] -= bookings[i][2];
        }
        //计算每趟航班预订的座位数
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                a[i] = tag[i + 1];
            } else {
                a[i] = a[i - 1] + tag[i + 1];
            }
        }
        //直接返回即可
        return a;
    }

    @Test
    public void corpFlightBookings() {
        String ints = "[[1,2,10],[2,3,20],[2,5,25]]";
        int[][] bookings = ParamUtils.getInts(ints);
        int n = 5;
        long start = System.currentTimeMillis();
        Object result = result2(bookings, n);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        PrintUtils.print(result);
    }
}
