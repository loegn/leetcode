package daily;

import utils.PrintUtils;

import java.util.*;

/**
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 */
public class No406 {
    /**
     * 从低到高考虑
     */
    public int[][] reconstructQueue1(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person1[0] - person2[0];
                } else {
                    return person2[1] - person1[1];
                }
            }
        });
        int n = people.length;
        int[][] ans = new int[n][];
        for (int[] person : people) {
            int spaces = person[1] + 1;
            for (int i = 0; i < n; ++i) {
                if (ans[i] == null) {
                    --spaces;
                    if (spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 从高到低考虑
     */
    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }

    /**
     *
     */
    public int[][] reconstructQueue3(int[][] people) {
        Map<Integer, PriorityQueue<int[]>> map = new TreeMap<>();
        for (int[] person : people) {
            int key = person[1];
            if (!map.containsKey(key)) {
                if (key == 0) {
                    map.put(key, new PriorityQueue<>(Comparator.comparingInt(a -> a[0])));
                } else {
                    map.put(key, new PriorityQueue<>(Comparator.comparingInt(a -> -a[0])));
                }
            }
            map.get(key).offer(person);
        }
        List<int[]> result = new LinkedList<>();
        for (Map.Entry<Integer, PriorityQueue<int[]>> entry : map.entrySet()) {
            int key = entry.getKey();
            if (key == 0) {
                PriorityQueue<int[]> queue = entry.getValue();
                while (!queue.isEmpty()){
                    result.add(queue.poll());
                }
            } else {
                PriorityQueue<int[]> queue = entry.getValue();
                while (!queue.isEmpty()){
                    int[] ints = queue.poll();
                    int index = 0;
                    int count = 0;
                    for (int[] curr : result) {
                        index++;
                        if (ints[0] <= curr[0]) {
                            if (++count == key) {
                                result.add(index, ints);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return result.toArray(people);
    }

    public static void main(String[] args) {
        No406 no406 = new No406();
        int[][] people = {{6,0},{5,0},{4,0},{3,2},{2,2},{1,4}};
        no406.reconstructQueue2(people);
        PrintUtils.print(people);
    }
}
