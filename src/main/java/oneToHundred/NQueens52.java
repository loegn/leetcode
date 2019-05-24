package oneToHundred;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @date : 2019/04/17 16:27
 * @author: liangenmao
 */
public class NQueens52 {
    /**
     * @see NQueens51
     */
    public int result1(int n) {
        helper(new ArrayList<String>(), 0, new boolean[n], new boolean[2*n], new boolean[2*n], n);
        return result;
    }

    private int result = 0;

    private void helper(List<String> board, int row, boolean[] cols, boolean[] d1, boolean[] d2, int n){
        if (row == n) {
            result++;
        }
        for (int col=0; col<n; col++){
            int id1 = col - row + n;
            int id2 = col + row;
            if (!cols[col] && !d1[id1] && !d2[id2]){
                char[] r = new char[n];
                Arrays.fill(r, '.');
                r[col] = 'Q';
                board.add(new String(r));
                cols[col] = true;
                d1[id1] = true;
                d2[id2] = true;
                helper(board, row+1, cols, d1, d2, n);
                board.remove(board.size()-1);
                cols[col] = false;
                d1[id1] = false;
                d2[id2] = false;
            }
        }
    }

    @Test
    public void solveNQueens() {
        int n = 4;
        Object result = result1(n);
        print(result);
    }

    private void print(Object result) {
        System.out.println(result);
        List<List<String>> lists = (List<List<String>>) result;
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println();
        }
    }

}
