package daily;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 */
public class No134 {
    /**
     * 一次遍历
     */
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }

    /**
     * 暴力解法
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int length = gas.length;
        if (length == 1) {
            //???
            return gas[0] >= cost[0] ? 0 : -1;
        }
        for (int i = 0; i < length; i++) {
            int curr = gas[i];
            for (int j = 1; j < length; j++) {
                curr -= cost[(i + j - 1) % length];
                if (curr < 0) {
                    break;
                }
                curr += gas[(j + i) % length];
                if (j == length - 1) {
                    int costNum = i == 0 ? cost[length - 1] : cost[i - 1];
                    if ((curr - costNum) >= 0) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        No134 no134 = new No134();
        int[] gas = {5};
        int[] cost = {4};
        int result = no134.canCompleteCircuit1(gas, cost);
        System.out.println(result);
    }
}
