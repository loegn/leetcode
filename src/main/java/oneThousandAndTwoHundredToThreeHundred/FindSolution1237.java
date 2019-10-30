package oneThousandAndTwoHundredToThreeHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @date : 2019/10/30 16:24:10
 * @author: liangenmao
 */
public class FindSolution1237 {
    private interface CustomFunction {
        int f(int x, int y);
    }

    public List<List<Integer>> result1(CustomFunction customfunction, int z) {
        List<List<Integer>> result = new ArrayList<>();
        final int init = 1;
        final int max = 1000;
        for (int x = init; x <= max; x++) {
            //TODO 可优化为二分查找法
            for (int y = init; y <= max; y++) {
                int f = customfunction.f(x, y);
                if (f >= z) {
                    if (f == z) {
                        List<Integer> list = new ArrayList<>();
                        list.add(x);
                        list.add(y);
                        result.add(list);
                    }
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 双指针
     *
     * @param customfunction
     * @param z
     * @return
     */
    public List<List<Integer>> result2(CustomFunction customfunction, int z) {
        List<List<Integer>> result = new ArrayList<>();
        int left = 1;
        int right = 1000;
        while (left <= 1000 && right >= 1) {
            int cal = customfunction.f(left, right);
            if (cal == z) {
                List<Integer> list = new ArrayList<>();
                list.add(left);
                list.add(right);
                result.add(list);
                left++;
            } else if (cal > z) {
                right--;
            } else {
                left++;
            }
        }
        return result;
    }

    @Test
    public void findSolution() {
        CustomFunction customfunction = null;
        int z = 0;
        Object result = result1(customfunction, z);
        PrintUtils.print(result);
    }
}
