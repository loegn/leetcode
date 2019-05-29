package datastructure;

import com.sun.jmx.remote.internal.ArrayQueue;
import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.*;

/**
 * @see <a href="https://leetcode-cn.com/explore/learn/card/queue-stack/217/queue-and-bfs/873/"></a>
 * @date : 2019/05/28 17:11:59
 * @author: liangenmao
 */
public class OpenLock {
    public int result1(String[] deadends, String target) {
        String start = "0000";
        Set<String> visited = new HashSet<>();
        for (int i = 0; i < deadends.length; i++) {
            visited.add(deadends[i]);
        }
        if (visited.contains(start)) {
            return -1;
        }
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        int[] way = {-1, 1};
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (Objects.equals(curr, target)) {
                    return count;
                }
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 2; k++) {
                        char[] chars = curr.toCharArray();
                        int digit = (chars[j] - '0' + way[k] + 10) % 10;
                        chars[j] = (char) ('0' +digit);
                        String next = new String(chars);
                        if (!visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                }
            }
            count++;
        }
        return -1;
    }

    @Test
    public void openLock() {
        String[] deadends = {"1234", "1234"};
        String target = "1235";
        Object result = result1(deadends, target);
        PrintUtils.print(result);
    }
}
