
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Cell implements KeyListener {

    private JTextField cell;
    private int correctValue;

    /**
     * Initialize cell and set text field
     *
     * @param value int
     * @param correctValue
     */
    public Cell(int value, int correntValue) {
        this.correctValue = correntValue;
        if (value == 0) { // if empty cell
            cell = new JTextField();
            cell.setForeground(Color.blue);
        } else { // if numbered cell
            cell = new JTextField("" + value);
            cell.setEditable(false);
            cell.setForeground(Color.black);
        }
        //set font of cell and make text in center
        cell.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        cell.setHorizontalAlignment(JTextField.CENTER); // center text
        cell.addKeyListener(this);

    }

    /**
     * Get cell
     *
     * @return JTextField
     */
    public JTextField getCell() {
        return cell;
    }

    /**
     * Get value
     *
     * @return int
     */
    public int getValue() {
        return Integer.parseInt(cell.getText());
    }

    /**
     * Change value
     *
     * @param value
     */
    public void setValue(int value) {
        cell.setText("" + value);
    }

    /**
     * Return true if value entered on cell is correct, make value green and
     * disable cell. otherwise return false
     *
     * @return boolean
     */
    public boolean isCorrect() {
        if (cell.getText().isEmpty()) // if empty
            return false;
        else if (Integer.parseInt(cell.getText()) == correctValue) // if correct number
            return true;
        else // if not correct
            return false;

    }

    /**
     * Solve the cell by setting the correct value
     */
    public void solve() {
        if (cell.getForeground() != Color.black) { // if not black (orignal) solve it
            cell.setText("" + correctValue);
            disable();
        }
    }

    /**
     * The value on cell is correct so disable
     */
    private void disable() {
        cell.setForeground(Color.green);
        cell.setEditable(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //if not from 1 to 9 don't write it
        if (e.getKeyChar() < '1' || e.getKeyChar() > '9')
            e.consume();
        //if already has value consume
        if (cell.getText().length() > 0)
            e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //if nothing enetered
        if (cell.getText().isEmpty()) {
            return;
        }
        //check key released if c
        if (e.getKeyCode() == KeyEvent.VK_C) {
            if (!isCorrect()) {
                JOptionPane.showMessageDialog(null, "Not correct!!");
            } else {
                disable();
                JOptionPane.showMessageDialog(null, "Correct!!");
            }
            cell.setText(cell.getText().substring(0, 1));
        }
    }
}
