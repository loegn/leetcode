package datastructure;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @date : 2019/05/29 11:37:00
 * @author: liangenmao
 */
public class NumSquares {
    /**
     * 四平方和
     *
     * @see <a href=https://zh.wikipedia.org/wiki/%E5%9B%9B%E5%B9%B3%E6%96%B9%E5%92%8C%E5%AE%9A%E7%90%86></a>
     */
    public int result1(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }
        if (n % 8 == 7) {
            return 4;
        }
        for (int a = 0; a * a <= n; ++a) {
            int b = (int) Math.sqrt(n - a * a);
            if (a * a + b * b == n) {
                if (a == 0) {
                    return 1;
                }
                return 2;
            }
        }
        return 3;
    }

    /**
     * 动态规划
     */
    public int result2(int n) {
        int[] steps = new int[n + 1];
        steps[0] = 0;
        int[] squares = new int[(int) Math.sqrt(n)];
        for (int i = 0; i < squares.length; i++) {
            squares[i] = (i + 1) * (i + 1);
        }
        for (int i = 1; i < steps.length; i++) {
            steps[i] = Integer.MAX_VALUE;
            for (int j = 0; j < squares.length; j++) {
                int diff = i - squares[j];
                if (diff >= 0) {
                    steps[i] = Math.min(steps[i], (steps[diff] + 1));
                }
            }
        }
        return steps[n];
    }

    /**
     * BFS
     */
    public int result3(int n) {
        int step = 0;
        int[] squares = new int[(int) Math.sqrt(n)];
        for (int i = 0; i < squares.length; i++) {
            squares[i] = (i + 1) * (i + 1);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                for (int j = 0; j < squares.length; j++) {
                    int next = curr - squares[j];
                    if (next == 0) {
                        return step;
                    }
                    if (next > 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return -1;
    }

    @Test
    public void numSquares() {
        int n = 7168;
        Object result = result1(n);
        PrintUtils.print(result);
    }
}
