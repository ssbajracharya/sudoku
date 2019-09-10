
public class SudokuSolver {

    /**
     * Solve sudoku and return solution
     *
     * @param mat int[][]
     * @return int[][]
     */
    public static int[][] solve(int[][] mat) {
        //copy sudoku
        int[][] solution = new int[mat.length][mat[0].length];
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[i].length; j++) {
                solution[i][j] = mat[i][j];
            }
        }
        solve(0, 0, solution);
        return solution;
    }

    /**
     * Solve sudoku and return true if solvable, otherwise return false
     *
     * @param row int
     * @param col int
     * @param cells int[][]
     * @return
     */
    private static boolean solve(int row, int col, int[][] cells) {
        if (row == 9) {
            row = 0;
            if (++col == 9)
                return true;
        }
        if (cells[row][col] != 0)  // skip filled cells
            return solve(row + 1, col, cells);

        for (int val = 1; val <= 9; ++val) {
            if (legal(row, col, val, cells)) {
                cells[row][col] = val;
                if (solve(row + 1, col, cells))
                    return true;
            }
        }
        cells[row][col] = 0; // reset on backtrack
        return false;
    }

    /**
     * Return true if legal value at cell, otherwise false
     *
     * @param row int
     * @param col int
     * @param val int
     * @param cells int[][]
     * @return boolean
     */
    private static boolean legal(int row, int col, int val, int[][] cells) {
        for (int i = 0; i < 9; i++)  // row
            if (val == cells[i][col])
                return false;
        for (int i = 0; i < 9; i++) // col
            if (val == cells[row][i])
                return false;
        int rowOffset = (row / 3) * 3;
        int colOffset = (col / 3) * 3;
        for (int i = 0; i < 3; i++) // box
            for (int j = 0; j < 3; j++)
                if (val == cells[rowOffset + i][colOffset + j])
                    return false;

        return true; // no violations, so it's legal
    }
}
