package datastructure;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.*;

/**
 * @date : 2019/06/06 16:58:51
 * @author: liangenmao
 */
public class CanVisitAllRooms {
    public boolean result1(List<List<Integer>> rooms) {
        int num = 0;
        boolean[] visited = new boolean[rooms.size()];
        Queue<Integer> keysQueue = new LinkedList<>();
        keysQueue.add(0);
        while (!keysQueue.isEmpty()) {
            int room = keysQueue.poll();
            if (!visited[room]) {
                visited[room] = true;
                num++;
                List<Integer> keysList = rooms.get(room);
                for (int i = 0; i < keysList.size(); i++) {
                    if (!visited[keysList.get(i)]) {
                        keysQueue.add(keysList.get(i));
                    }
                }
            }
        }
        return num == rooms.size();
    }

    public static boolean result2(List<List<Integer>> rooms) {
        int flag[] = new int[rooms.size()];
        dfs(rooms, flag, 0);
        flag[0] = 1;
        for (int tmp : flag
        ) {
            if (tmp < 1) {
                return false;
            }
        }
        return true;
    }

    public static void dfs(List<List<Integer>> rooms, int flag[], int start) {
        if (flag[start] > 1 || rooms.get(start).size() == 0) {
            return;
        }
        List<Integer> tmp = rooms.get(start);
        for (int k : tmp
        ) {
            flag[k] += 1;
            dfs(rooms, flag, k);

        }
    }

    @Test
    public void canVisitAllRooms() {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1, 3));
        rooms.add(Arrays.asList(3, 0, 1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(0));
        Object result = result1(rooms);
        PrintUtils.print(result);
    }
}
