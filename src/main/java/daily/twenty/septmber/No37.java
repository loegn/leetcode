package daily.twenty.septmber;

public class No37 {
    public void solveSudoku(char[][] board) {
        int[] row = new int[9];
        int[] column = new int[9];
        int[] grid = new int[9];
        boolean[][] set = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int value = board[i][j] - '0';
                    row[i] += 1 << value;
                    column[j] += 1 << value;
                    grid[i / 3 * 3 + j / 3] += 1 << value;
                    set[i][j] = true;
                }
            }
        }
        solve(board, set, row, column, grid, 0, 0);
    }

    private void solve(char[][] board, boolean[][] set, int[] row, int[] column, int[] grid, int rowIndex, int columnIndex) {
        for (int i = rowIndex; i < 9; i++) {
            for (int j = columnIndex; j < 9; j++) {
                if (!set[i][j]) {
                    for (int k = 1; k <= 9; k++) {
                        if (((row[i] >> k) & 1) == 0 && ((column[j] >> k) & 1) == 0 && ((grid[i / 3 * 3 + j / 3] >> k) & 1) == 0) {
                            row[i] += 1 << k;
                            column[j] += 1 << k;
                            grid[i / 3 * 3 + j / 3] += 1 << k;
                            set[i][j] = true;
                            //TODO
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
    }
}
