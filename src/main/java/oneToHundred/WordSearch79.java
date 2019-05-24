package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import utils.ParamUtils;

/**
 * @date : 2019/05/07 10:40:30
 * @author: liangenmao
 */
public class WordSearch79 {
    /**
     * 遍历board寻找初始点，递归寻找下个点是否存在
     * 一开始没考虑走过的点不能走了
     */
    public boolean result1(char[][] board, String word) {
        boolean[][] putted = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    putted[i][j] = true;
                    if (find(putted, board, word, i, j, 1)) {
                        return true;
                    }
                    putted[i][j] = false;
                }
            }
        }
        return false;
    }

    private boolean find(boolean[][] putted, char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }
        char value = word.charAt(index);
        i--;
        if (i >= 0 && board[i][j] == value && putted[i][j] == false) {
            putted[i][j] = true;
            if (find(putted, board, word, i, j, index + 1)) {
                return true;
            }
            putted[i][j] = false;
        }
        i++;
        i++;
        if (i < board.length && board[i][j] == value && putted[i][j] == false) {
            putted[i][j] = true;
            if (find(putted, board, word, i, j, index + 1)) {
                return true;
            }
            putted[i][j] = false;
        }
        i--;
        j--;
        if (j >= 0 && board[i][j] == value && putted[i][j] == false) {
            putted[i][j] = true;
            if (find(putted, board, word, i, j, index + 1)) {
                return true;
            }
            putted[i][j] = false;
        }
        j++;
        j++;
        if (j < board[i].length && board[i][j] == value && putted[i][j] == false) {
            putted[i][j] = true;
            if (find(putted, board, word, i, j, index + 1)) {
                return true;
            }
            putted[i][j] = false;
        }
        return false;
    }

    /**
     * 不需要额外空间
     */
    public boolean result2(char[][] board, String word) {
        if (board == null || word == null) {
            return false;
        }
        if (word.length() == 0) {
            return true;
        }

        char[] w = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (exist(board, i, j, w, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean exist(char[][] board, int r, int c, char[] word, int index) {
        if (board[r][c] == word[index]) {
            char save = board[r][c];
            board[r][c] = 0;
            if ((index == word.length - 1) ||
                    (r > 0 && exist(board, r - 1, c, word, index + 1)) ||
                    (c > 0 && exist(board, r, c - 1, word, index + 1)) ||
                    (r < board.length - 1 && exist(board, r + 1, c, word, index + 1)) ||
                    (c < board[r].length - 1 && exist(board, r, c + 1, word, index + 1))) {
                return true;
            }
            board[r][c] = save;
        }
        return false;
    }

    @Test
    public void exist() {
        String chars = "[['a','a']]";
        char[][] board = ParamUtils.getChars(chars);
        String word = "aaa";
        Object result = result1(board, word);
        PrintUtils.print(result);
    }
}
