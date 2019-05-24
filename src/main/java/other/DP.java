package other;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @date : 2019/04/12 14:32
 * @author: liangenmao
 */
public class DP {
    public int result1(int target) {
        Arrays.sort(MONEY);
        int maxMoney = MONEY[MONEY.length - 1];
        int count = target / maxMoney;
        target = target >= maxMoney ? target % maxMoney : target;
        costs = new int[target + 1];
        for (int i = 1; i < costs.length; i++) {
            int cost = Integer.MAX_VALUE;
            for (int j = 0; j < MONEY.length; j++) {
                if (i - MONEY[j] >= 0) {
                    cost = Math.min(cost, costs[i - MONEY[j]] + 1);
                }
            }
            costs[i] = cost;
        }
        return costs[target] + count;
    }

    private int[] costs;

    private static final int[] MONEY = {1, 5, 10, 20, 50, 100};

    @Test
    public void money() {
        int target = 115;
        Object result = result1(target);
        System.out.println(result);
    }
}
