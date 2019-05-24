package oneToHundred;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @date : 2019/04/11 10:02
 * @author: liangenmao
 */
public class ValidSudoku {
    public boolean result1(char[][] board) {
        //0:row;1:column;2:grid
        Set<Character>[][] sets = new HashSet[3][9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                sets[i][j] = new HashSet<>();
            }
        }
        for (int i = 0; i < board.length; i++) {
            char[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                if (!validSet(sets[0][i], board[i][j]) || !validSet(sets[1][j], board[i][j]) || !validSet(sets[2][getGrid(i, j)], board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getGrid(int row, int column) {
        return row / 3 * 3 + column / 3;
    }

    private static final char BLANK = '.';

    private boolean validSet(Set<Character> set, char value) {
        if (value == BLANK) {
            return true;
        }
        if (set.contains(value)) {
            return false;
        } else {
            set.add(value);
            return true;
        }
    }

    public boolean result2(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] blks = new int[9];
        for(int ri = 0; ri < 9; ++ri){
            for(int ci = 0; ci < 9; ++ci){
                if(board[ri][ci] != '.'){
                    int bi = ri / 3 * 3 + ci / 3;
                    int uvb = 1 << (board[ri][ci] - '1');
                    if((uvb & (rows[ri] | cols[ci] | blks[bi])) != 0) {
                        return false;
                    }
                    rows[ri] |= uvb;
                    cols[ci] |= uvb;
                    blks[bi] |= uvb;
                }
            }
        }
        return true;
    }

    @Test
    public void isValidSudoku() {
        char[][] board = new char[9][9];
        Object result = result1(board);
        System.out.println(result);
    }
}
