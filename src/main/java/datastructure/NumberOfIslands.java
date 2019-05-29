package datastructure;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @see <a href="https://leetcode-cn.com/explore/learn/card/queue-stack/217/queue-and-bfs/872/"></a>
 * @date : 2019/05/28 15:32:29
 * @author: liangenmao
 */
public class NumberOfIslands {
    /**
     * DFS
     */
    public int result1(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    helper(grid, visited, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    private void helper(char[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length) {
            return;
        }
        if (grid[i][j] == '1' && !visited[i][j]) {
            visited[i][j] = true;
            helper(grid, visited, i - 1, j);
            helper(grid, visited, i + 1, j);
            helper(grid, visited, i, j - 1);
            helper(grid, visited, i, j + 1);
        }
    }

    /**
     * BFS
     * 未测试
     */
    public int result2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int[] dirX = {0, -1, 0, 1, 0};
        int[] dirY = {0, 0, 1, 0, -1};
        int result = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    result++;
                    //可以用i*grid[0].length+j来储存i,j的值
                    Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
                    queue.offer(new Pair<>(i, j));
                    while (!queue.isEmpty()) {
                        Pair<Integer, Integer> pair = queue.poll();
                        for (int i1 = 0; i1 < 5; i1++) {
                            int x = pair.getKey() + dirX[i1];
                            int y = pair.getValue() + dirY[i1];
                            if (x < 0 || x >= grid.length || y < 0 || y >= grid.length || grid[x][y] == '0' || visited[x][y]) {
                                visited[x][y] = true;
                                queue.offer(new Pair<>(x, y));
                            }
                        }
                    }
                }
            }
        }
        return result;
    }


    @Test
    public void numIslands() {
        String chars = "";
        char[][] grid = ParamUtils.getChars(chars);
        Object result = result1(grid);
        PrintUtils.print(result);
    }
}
