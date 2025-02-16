package view;

import javax.swing.*;

import sudoko.Location;
import sudoko.SolverThread;
import sudoko.SudokoSolverListener;

import java.awt.*;
import java.awt.event.*;

public class GUI implements MouseListener, WindowListener, KeyListener, SudokoSolverListener {
    private Tile highlightedTile = null;
    private JFrame gui = new JFrame("Sudoku Solver");
    private Tile[][] board = new Tile[9][9];

    private boolean isKeyEventProcessed = false;

    public GUI() {
        gui.addWindowListener(this);
        gui.setLayout(new BorderLayout());

        JPanel sudokuPanel = new JPanel();
        sudokuPanel.setLayout(new GridLayout(3, 3));
        for (int subgridNumber = 0; subgridNumber < 9; subgridNumber++) {
            JPanel subgrid = new JPanel();
            subgrid.setLayout(new GridLayout(3, 3));
            subgrid.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            for (int i = 0; i < 9; i++) {
                Tile tile = new Tile(((subgridNumber / 3) * 3 + i / 3), (subgridNumber % 3) * 3 + i % 3);
                board[tile.getRow()][tile.getColumn()] = tile;
                tile.addMouseListener(this);
                tile.addKeyListener(this);
                subgrid.add(tile);
            }
            sudokuPanel.add(subgrid);
        }
        gui.add(sudokuPanel, BorderLayout.CENTER);

        // Adding control buttons
        JPanel controlPanel = new JPanel();
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> solve());
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetBoard());

        controlPanel.add(solveButton);
        controlPanel.add(resetButton);
        gui.add(controlPanel, BorderLayout.SOUTH);

        gui.setBounds(100, 100, 600, 600);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }

    private void highlightTile(Tile tile) {
        removeHighlight();
        tile.setBackground(Color.cyan);
        tile.repaint();
        highlightedTile = tile;
    }

    private void removeHighlight() {
        if (highlightedTile != null) {
            highlightedTile.setBackground(Color.WHITE);
            highlightedTile.repaint();
            highlightedTile = null;
        }
    }

    private void changeHighlightedNumber(int number) {
        System.out.println(number);
        if (highlightedTile != null) {
            highlightedTile.setNumber(number);
            removeHighlight();
        }
    }

    private void solve() {
        int[][] boardInt = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                boardInt[row][column] = board[row][column].getNumber();
            }
        }
        SolverThread sudokuSolverThread = new SolverThread(boardInt, this);
        sudokuSolverThread.start();
    }

    private void resetBoard() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                board[row][column].setNumber(0);
                board[row][column].updateUI();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!isKeyEventProcessed && highlightedTile != null) {
            char clickedChar = e.getKeyChar();
            if(Character.isLetter(clickedChar)){
                JOptionPane.showMessageDialog(
                        gui,
                        "Cannot set character. Please enter a number between 0 and 9.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
                highlightedTile.setText("");
            }
            else if(Character.isDigit(clickedChar) && clickedChar == '0'){
                JOptionPane.showMessageDialog(
                        gui,
                        "Cannot set zero. Please enter a number between 1 and 9.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
                highlightedTile.setText("");
            }
            else if (Character.isDigit(clickedChar)) {
                int number = Character.digit(clickedChar, 10);
                // Update the number and set the flag to true
                changeHighlightedNumber(number);
                isKeyEventProcessed = true;
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                solve();
            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                changeHighlightedNumber(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        isKeyEventProcessed = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component clickedComponent = e.getComponent();
        if (clickedComponent instanceof Tile clickedTile) {
            highlightTile(clickedTile);
            clickedTile.requestFocusInWindow(); // Ensure focus is set to the highlighted tile
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void tileChanged(Location tileLocation, int newValue) {
        SwingUtilities.invokeLater(() -> board[tileLocation.row][tileLocation.column].setNumber(newValue));
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}

