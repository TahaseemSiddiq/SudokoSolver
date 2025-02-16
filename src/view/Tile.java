package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

public class Tile extends JButton {
    private final int row;
    private final int column;

    public Tile(int row, int column) {
        super();
        this.row = row;
        this.column = column;
        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Arial", Font.BOLD, 20));
        setPreferredSize(new Dimension(50, 50));
        setFocusTraversalKeysEnabled(false);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getNumber() {
        try {
            return Integer.parseInt(getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setNumber(int number) {
        if (number >= 0 && number <= 9) {
            setText(number == 0 ? "" : String.valueOf(number));
        }
    }
}
