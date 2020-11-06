package daily;

import utils.PrintUtils;

import java.util.*;

public class No1356 {
    /**
     * 暴力
     */
    public int[] sortByBits1(int[] arr) {
        int[] bit = new int[10001];
        List<Integer> list = new ArrayList<Integer>();
        for (int x : arr) {
            list.add(x);
            bit[x] = get(x);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                if (bit[x] != bit[y]) {
                    return bit[x] - bit[y];
                } else {
                    return x - y;
                }
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 2;
            x /= 2;
        }
        return res;
    }

    /**
     * 递推预处理
     */
    public int[] sortByBits2(int[] arr) {
        List<Integer> list = new ArrayList<Integer>();
        for (int x : arr) {
            list.add(x);
        }
        int[] bit = new int[10001];
        for (int i = 1; i <= 10000; ++i) {
            bit[i] = bit[i >> 1] + (i & 1);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                if (bit[x] != bit[y]) {
                    return bit[x] - bit[y];
                } else {
                    return x - y;
                }
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    /**
     * 原地存储，不开辟新的空间
     */
    public int[] sortByBits3(int[] arr) {
        int length = arr.length;

        /*
            根据 1的个数 和 当前数值，存储 每一个数字：
                此处是本题解的精髓：1的个数权值最大，其次是本身的值，方便之后的 还原和排序
         */
        for (int i = 0; i < length; i++) {
            //i最大为10000，需要14个bit存储
            arr[i] = Integer.bitCount(arr[i]) << 15 | arr[i];
        }

        /*
            将 存储的数字，还原成最初始的数字，并根据 1的个数 和 当前数值 排序
         */
        Arrays.sort(arr);
        for (int i = 0; i < length; i++) {
            arr[i] &= 0x3FFF;
        }
        return arr;
    }

    public int[] sortByBits4(int[] arr) {
        Arrays.sort(arr);
        int length = 15;
        List<Integer>[] lists = new List[length];
        for (int i = 0; i < length; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            int value = arr[i];
            while (value > 0) {
                if ((value & 1) == 1) {
                    count++;
                }
                value >>= 1;
            }
            lists[count].add(arr[i]);
        }
        int index = 0;
        int[] result = new int[arr.length];
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                result[index] = integer;
                index++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int i = Integer.MAX_VALUE;
//        System.out.println(Integer.toBinaryString(i));
//        i <<= 1;
//        System.out.println(Integer.toBinaryString(i));
        No1356 no1356 = new No1356();
        int[] input = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] result = no1356.sortByBits3(input);
        PrintUtils.print(result);
    }
}
