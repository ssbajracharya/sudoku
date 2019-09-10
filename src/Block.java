
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

public class Block extends JPanel {

    private Cell[][] cells;

    public Block() {
        cells = new Cell[3][3];
        setLayout(new GridLayout(3, 3, 3, 3));
        setBackground(Color.lightGray);
    }

    /**
     * Set cell at pos
     *
     * @param row int
     * @param col int
     * @param cell Cell
     */
    public void addCell(int row, int col, Cell cell) {
        cells[row][col] = cell;
        add(cells[row][col].getCell());
    }

    /**
     * Get cell at pos
     *
     * @param row int
     * @param col int
     * @return
     */
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    /**
     * Return true if complete otherwise false
     *
     * @return boolean
     */
    public boolean isComplete() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (!cells[i][j].isCorrect())
                    return false;
            }
        }
        return true;
    }

    /**
     * Solve sudoku (give up option)
     */
    public void solve(){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j].solve();
            }
        }
    }
}
