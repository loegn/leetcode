package daily;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No79 {
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        boolean[][] used = new boolean[board.length][];
        for (int i = 0; i < board.length; i++) {
            used[i] = new boolean[board[i].length];
        }
        char begin = word.charAt(0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == begin) {
                    used[i][j] = true;
                    if (search(board, used, i, j, word, 1)) {
                        return true;
                    }
                    used[i][j] = false;
                }
            }
        }
        return false;
    }

    private boolean search(char[][] board, boolean[][] used, int row, int column, String word, int index) {
        if (index == word.length()) {
            return true;
        }
        char curr = word.charAt(index);
        //up
        int i = row - 1;
        int j = column;
        if (i >= 0 && j < board[i].length && board[i][j] == curr && !used[i][j]) {
            used[i][j] = true;
            if (search(board, used, i, j, word, index + 1)) {
                return true;
            }
            ;
            used[i][j] = false;
        }
        //down
        i = row + 1;
        j = column;
        if (i < board.length && j < board[i].length && board[i][j] == curr && !used[i][j]) {
            used[i][j] = true;
            if (search(board, used, i, j, word, index + 1)) {
                return true;
            }
            ;
            used[i][j] = false;
        }
        //left
        i = row;
        j = column - 1;
        if (j >= 0 && board[i][j] == curr && !used[i][j]) {
            used[i][j] = true;
            if (search(board, used, i, j, word, index + 1)) {
                return true;
            }
            ;
            used[i][j] = false;
        }
        //right
        i = row;
        j = column + 1;
        if (j < board[i].length && board[i][j] == curr && !used[i][j]) {
            used[i][j] = true;
            if (search(board, used, i, j, word, index + 1)) {
                return true;
            }
            ;
            used[i][j] = false;
        }
        return false;
    }
}
