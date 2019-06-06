package datastructure;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @date : 2019/06/06 11:06:24
 * @author: liangenmao
 */
public class FloodFill {
    public int[][] result1(int[][] image, int sr, int sc, int newColor) {
        int srcColor = image[sr][sc];
        if (srcColor == newColor) {
            return image;
        }
        Queue<Map.Entry<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new HashMap.SimpleEntry<>(sr, sc));
        int[][] around = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        image[sr][sc] = newColor;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Map.Entry<Integer, Integer> entry = queue.poll();
                for (int j = 0; j < around.length; j++) {
                    int x = entry.getKey() + around[j][0];
                    int y = entry.getValue() + around[j][1];
                    if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && image[x][y] == srcColor) {
                        queue.offer(new HashMap.SimpleEntry<>(x, y));
                        image[x][y] = newColor;
                    }
                }
            }
        }
        return image;
    }

    @Test
    public void floodFill() {
        String ints = "";
        int[][] image = ParamUtils.getInts(ints);
        int sr = 0;
        int sc = 0;
        int newColor = 0;
        Object result = result1(image, sr, sc, newColor);
        PrintUtils.print(result);
    }
}
