package daily;

import utils.PrintUtils;

import java.util.*;

/**
 * Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)
 * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的一项：
 * 禁止一名参议员的权利：
 * 参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
 * 宣布胜利：
 * 如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。
 * 给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
 * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
 * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。
 */
public class No649 {
    /**
     * 官方解答
     * @param senate
     * @return
     */
    public String predictPartyVictory1(String senate) {
        int n = senate.length();
        Queue<Integer> radiant = new LinkedList<Integer>();
        Queue<Integer> dire = new LinkedList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int radiantIndex = radiant.poll(), direIndex = dire.poll();
            if (radiantIndex < direIndex) {
                radiant.offer(radiantIndex + n);
            } else {
                dire.offer(direIndex + n);
            }
        }
        return !radiant.isEmpty() ? "Radiant" : "Dire";
    }

    public String predictPartyVictory2(String senate) {
        int rRemove = 0;
        int dRemove = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'D') {
                if (dRemove > 0) {
                    dRemove--;
                } else {
                    deque.add(2);
                    rRemove++;
                }
            } else {
                if (rRemove > 0) {
                    rRemove--;
                } else {
                    deque.add(1);
                    dRemove++;
                }
            }
        }
        int leave;
        while ((leave = oneside(deque)) == -1) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int value = deque.poll();
                if (value == 1) {
                    if (rRemove > 0) {
                        rRemove--;
                    } else {
                        deque.add(1);
                        dRemove++;
                    }
                } else {
                    if (dRemove > 0) {
                        dRemove--;
                    } else {
                        deque.add(2);
                        rRemove++;
                    }
                }
            }
        }
        return leave == 1 ? "Radiant" : "Dire";
    }

    private int oneside(Deque<Integer> deque) {
        Boolean r = null;
        for (Integer integer : deque) {
            if (r == null) {
                r = integer == 1;
            } else {
                if (r != (integer == 1)) {
                    return -1;
                }
            }
        }
        return r ? 1 : 2;
    }

    public static void main(String[] args) {
        No649 no649 = new No649();
        Object result = no649.predictPartyVictory1("RD");
        PrintUtils.print(result);
    }
}
