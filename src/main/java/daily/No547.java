package daily;

import utils.ParamUtils;
import utils.PrintUtils;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/number-of-provinces/
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 */
public class No547 {

    /**
     * 深度优先搜索
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum1(int[][] isConnected) {
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, provinces, i);
                circles++;
            }
        }
        return circles;
    }

    public void dfs(int[][] isConnected, boolean[] visited, int provinces, int i) {
        for (int j = 0; j < provinces; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, provinces, j);
            }
        }
    }

    /**
     * 广度优先搜索
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum2(int[][] isConnected) {
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        int circles = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < provinces; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                circles++;
            }
        }
        return circles;
    }

    /**
     * 并查集
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum3(int[][] isConnected) {
        int provinces = isConnected.length;
        int[] parent = new int[provinces];
        for (int i = 0; i < provinces; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < provinces; i++) {
            for (int j = i + 1; j < provinces; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            if (parent[i] == i) {
                circles++;
            }
        }
        return circles;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }

    /**
     * 我的解答
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum4(int[][] isConnected) {
        int length = isConnected.length;
        int index = 1;
//        int count = 0;
        int[] province = new int[length];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (isConnected[i][j] == 1) {
                    int a = province[i];
                    int b = province[j];
                    if (a == 0) {
                        province[i] = index;
//                        province[j] = index;
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        map.put(index, list);
                        index++;
                    } else {
                        if (b == 0) {
                            province[j] = province[i];
                            map.get(province[i]).add(j);
                        } else {
                            if (a != b) {
                                int other = province[j];
                                int origin = province[i];
                                map.get(origin).addAll(map.get(other));
                                for (Integer integer : map.get(other)) {
                                    province[integer] = origin;
                                }
                                map.remove(other);
                            }
                        }
                    }
                }
            }
        }
        return map.size();
    }

    public static void main(String[] args) {
        No547 no547 = new No547();
//        int[][] input = ParamUtils.getInts("[[1,1,0],[1,1,0],[0,0,1]]");
        int[][] input = ParamUtils.getInts("[[1,0,0,1],[0,1,1,0],[0,1,1,1],[1,0,1,1]]");
        Object output = no547.findCircleNum4(input);
        PrintUtils.print(output);
    }
}
