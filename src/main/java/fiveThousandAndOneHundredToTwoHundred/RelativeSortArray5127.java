package fiveThousandAndOneHundredToTwoHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @date : 2019/07/15 14:08:30
 * @author: liangenmao
 */
public class RelativeSortArray5127 {
    public int[] result1(int[] arr1, int[] arr2) {
        if (arr1.length == 0) {
            return arr1;
        }
        int[] result = new int[arr1.length];
        //value,count
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr2) {
            map.put(i, 0);
        }
        int index = result.length;
        for (int i : arr1) {
            if (map.get(i) != null) {
                map.put(i, map.get(i) + 1);
            } else {
                result[--index] = i;
            }
        }
        index = 0;
        for (int num : arr2) {
            for (int i = 0; i < map.get(num); i++) {
                result[index] = num;
                index++;
            }
        }
        if (index < result.length) {
            Arrays.sort(result, index, result.length);
        }
        return result;
    }

    @Test
    public void relativeSortArray() {
        String ints = "[2,3,1,3,2,4,6,7,9,2,19]";
        String ints2 = "[2,1,4,3,9,6]";
        int[] arr1 = ParamUtils.getInt(ints);
        int[] arr2 = ParamUtils.getInt(ints2);
        Object result = result1(arr1, arr2);
        PrintUtils.print(result);
//        [2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19]
    }
}
