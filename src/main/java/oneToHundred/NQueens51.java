package oneToHundred;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @date : 2019/04/17 16:27
 * @author: liangenmao
 */
public class NQueens51 {
    /**
     *  想错了递归回溯
     *  @see #result3(int)
     */
    public List<List<String>> result1(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        boolean[][] row = new boolean[n][n];
        boolean[][] column = new boolean[n][n];
        boolean[][] luInclinedRd = new boolean[2 * n - 1][n];
        boolean[][] ldInclinedRu = new boolean[2 * n - 1][n];
        find(board, row, column, luInclinedRd, ldInclinedRu, result, n, 0, 0);
        return result;
    }

    private void find(char[][] board, boolean[][] row, boolean[][] column, boolean[][] luInclinedRd, boolean[][] ldInclinedRu, List<List<String>> result, int n, int i, int i1) {

    }

    private static char queen = 'q';
    private static char blank = '.';

    /**
     *
     */
    public List<List<String>> result2(int n) {
        List<List<String>> solutions = new ArrayList<>();
        boolean[][] positions = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(positions[i], true);
        }

        backTracking(solutions, positions, 0);
        return solutions;
    }

    private void backTracking(List<List<String>> solutions, boolean[][] positions, int row) {

        int n = positions.length;
        if (row > n - 1) {
            solutions.add(generateLine(positions));
            return;
        }

        for (int i = 0; i < n; i++) {

            if (positions[row][i]) {
                boolean[][] copy = copyPositions(n, positions);
                // make false in the row
                for (int x = 0; x < n; x++) {
                    copy[row][x] = x == i ? true : false;
                }

                // make false in the column
                for (int x = 0; x < n; x++) {
                    copy[x][i] = x == row ? true : false;
                }

                // make false in the diagonal direction
                int x = 1;
                while (row + x < n && i + x < n) {
                    copy[row + x][i + x] = false;
                    x++;
                }

                x = 1;
                while (row + x < n && i - x >= 0) {
                    copy[row + x][i - x] = false;
                    x++;
                }

                backTracking(solutions, copy, row + 1);
            }
        }
    }

    private List<String> generateLine(boolean[][] positions) {
        List<String> solution = new ArrayList<>();
        int n = positions.length;
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (positions[i][j]) {
                    row.append("Q");
                } else {
                    row.append(".");
                }
            }
            solution.add(row.toString());
        }
        return solution;
    }

    private boolean[][] copyPositions(int n, boolean[][] positions) {
        boolean[][] copy = new boolean[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                copy[x][y] = positions[x][y];
            }
        }
        return copy;
    }

    /**
     * 递归回溯
     */
    public List<List<String>> result3(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        helper(result, new ArrayList<String>(), 0, new boolean[n], new boolean[2*n], new boolean[2*n], n);
        return result;
    }

    private void helper(List<List<String>> result, List<String> board, int row, boolean[] cols, boolean[] d1, boolean[] d2, int n){
        if (row == n) {
            result.add(new ArrayList<String>(board));
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
                helper(result, board, row+1, cols, d1, d2, n);
                board.remove(board.size()-1);
                cols[col] = false;
                d1[id1] = false;
                d2[id2] = false;
            }
        }
    }

    /**
     * dfs
     */
    public List<List<String>> result4(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<List<String>>();
        dfs(board, 0, res);
        return res;
    }

    private void dfs(char[][] board, int colIndex, List<List<String>> res) {
        if (colIndex == board.length) {
            res.add(construct(board));
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (validate(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfs(board, colIndex + 1, res);
                board[i][colIndex] = '.';
            }
        }
    }

    private boolean validate(char[][] board, int x, int y) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || x == i)) {
                    return false;
                }
            }
        }

        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }


    @Test
    public void solveNQueens() {
        int n = 4;
        Object result = result2(n);
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
