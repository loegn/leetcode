package oneToHundred;

import java.util.*;

/**
 * @date : 2019/02/27 17:05
 * @author: liangenmao
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] ints = {-1, 0, 1, 2, -1, -4};
        threeSum(ints);
    }


    public static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ls = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {  // 跳过可能重复的答案

                int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];
                while (l < r) {
                    if (nums[l] + nums[r] == sum) {
                        ls.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        l++;
                        r--;
                    } else if (nums[l] + nums[r] < sum) {
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;   // 跳过重复值
                        }
                        l++;
                    } else {
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        r--;
                    }
                }
            }
        }
        return ls;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Map<Integer, Integer>> resultMap = new HashMap<>();
        for (int i = 0; i < nums.length - 2; i++) {
            Map<Integer, Integer> map = new HashMap();
            for (int j = i + 1; j < nums.length; j++) {
                map.put(nums[j], j);
                if (map.containsKey(0 - nums[i] - nums[j])) {
                    addMap(resultMap, nums[i], nums[map.get(0 - nums[i] - nums[j])], nums[j]);
                }
            }
        }
        return toList(resultMap);
    }

    private static void addMap(Map<Integer, Map<Integer, Integer>> mapMap, int a, int b, int c) {
        if (!mapMap.containsKey(a)) {
            Map<Integer, Integer> map = new HashMap();
            mapMap.put(a, map);
        }
        if (mapMap.containsKey(a) && mapMap.get(a).containsKey(b) && mapMap.get(a).get(b) != c) {
            mapMap.get(a).put(b, c);
        }
    }

    private static List<List<Integer>> toList(Map<Integer, Map<Integer, Integer>> mapMap) {
        List<List<Integer>> lists = new ArrayList<>();
        Iterator<Map.Entry<Integer, Map<Integer, Integer>>> iterator = mapMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Map<Integer, Integer>> entry = iterator.next();
            Iterator<Map.Entry<Integer, Integer>> iterator1 = entry.getValue().entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry<Integer, Integer> entry1 = iterator1.next();
                List<Integer> list = new ArrayList<>();
                list.add(entry.getKey());
                list.add(entry1.getKey());
                list.add(entry1.getValue());
            }
        }
        return lists;
    }
}
