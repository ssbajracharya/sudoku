
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SudokuGUI extends JFrame implements ActionListener {

    private Block[][] sudoku = new Block[3][3];
    private JButton giveUp;
    private JButton check;
    private long startTime;
    private long endTime;

    public SudokuGUI() {
        startTime = System.currentTimeMillis();
        //initialize sudoku
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                sudoku[i][j] = new Block();
            }
        }
        //read random file from 4 files
        sudokuReader("samplesudoku" + ((int) (Math.random() * 4) + 1) + ".txt");
        //set frame constraints
        setTitle("Sudoku Solver");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //add to window
        JPanel pan = new JPanel(new GridLayout(3, 3, 3, 3));
        pan.setBackground(Color.black);
        pan.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                pan.add(sudoku[i][j]);
            }
        }
        add(pan, BorderLayout.CENTER);
        //add giveup button at bottom
        giveUp = new JButton("Give up");
        giveUp.addActionListener(this);
        check = new JButton("Check Solution");
        check.addActionListener(this);
        JPanel pan2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pan2.add(giveUp);
        pan2.add(check);
        add(pan2, BorderLayout.SOUTH);
    }

    private void sudokuReader(String filename) {
        try {
            Scanner in = new Scanner(new File(filename));
            int[][] values = new int[9][9];
            int[][] solution;
            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < values.length; j++) {
                    values[i][j] = in.nextInt();
                }
            }
            solution = SudokuSolver.solve(values);
            for (int i = 0; i < solution.length; i++) {
                for (int j = 0; j < solution.length; j++) {
                    sudoku[i / 3][j / 3].addCell(i % 3, j % 3, new Cell(values[i][j], solution[i][j]));
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Input data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Return true if complete, otherwise false
     *
     * @return boolean
     */
    private boolean isComplete() {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                if (!sudoku[i][j].isComplete())
                    return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == giveUp) {
            for (int i = 0; i < sudoku.length; i++) {
                for (int j = 0; j < sudoku.length; j++) {
                    sudoku[i][j].solve();
                }
            }
        } else if (isComplete()) {
            //close all grids
            for (int i = 0; i < sudoku.length; i++) {
                for (int j = 0; j < sudoku.length; j++) {
                    sudoku[i][j].solve();
                }
            }
            //get end time and show it
            endTime = System.currentTimeMillis();
            long totalTime = (endTime - startTime) / 1000;
            JOptionPane.showMessageDialog(this, "Congratlations, it took " + totalTime + " seconds to solve.");
            System.exit(0);
        } else { // not complete
            JOptionPane.showMessageDialog(this, "Not correct solution");
        }
    }

    public static void main(String[] args) {
        new SudokuGUI().setVisible(true);
    }
}
