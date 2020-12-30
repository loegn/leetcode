package daily;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/last-stone-weight/
 * 有一堆石头，每块石头的重量都是正整数。
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 */
public class No1046 {
    /**
     * 最大堆
     *
     * @param stones
     * @return
     */
    public int lastStoneWeight1(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a > b) {
                pq.offer(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }

    /**
     * 我的解答
     *
     * @param stones
     * @return
     */
    public int lastStoneWeight2(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int stone : stones) {
            queue.add(stone);
        }
        while (queue.size() > 1) {
            int diff = Math.abs(queue.poll() - queue.poll());
            if (diff > 0) {
                queue.add(diff);
            }
        }
        return queue.isEmpty() ? 0 : queue.poll();
    }
}

