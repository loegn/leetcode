package daily;

/**
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * 注意，一开始你手头没有任何零钱。
 * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 */
public class No860 {
    /**
     * 官方解答
     * @param bills
     * @return
     */
    public boolean lemonadeChange1(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) {
                    return false;
                }
                five--;
                ten++;
            } else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean lemonadeChange2(int[] bills) {
        int five = 0;
        int ten = 0;
        int twenty = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                ten++;
            } else if (bill == 20) {
                twenty++;
            } else {
                return false;
            }
            int change = bill - 5;
            if (change == 15) {
                if (ten > 0) {
                    ten--;
                    if (--five < 0) {
                        return false;
                    }
                } else {
                    five -= 3;
                    if (five < 0) {
                        return false;
                    }
                }
            } else if (change == 5) {
                if (--five < 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
