import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private int[][] board = new int[3][3];  // 0 - blank, 1 - X, 2 - O
    private int currentPlayer = 1; // X starts
    private JLabel statusLabel;

    public TicTacToe() {
        setTitle("Tic Tac Toe - Console to Canvas");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        Font font = new Font("Arial", Font.BOLD, 60);

        // Initialize buttons and board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
                board[i][j] = 0;
            }
        }

        // Status label to show current player or result
        statusLabel = new JLabel("Player X's turn");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Add components to frame
        add(panel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Find which button was clicked
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (e.getSource() == buttons[i][j]) {
                    if (board[i][j] == 0) {
                        // Update board and button text
                        board[i][j] = currentPlayer;
                        buttons[i][j].setText(currentPlayer == 1 ? "X" : "O");
                        buttons[i][j].setEnabled(false);

                        // Check if current player won
                        if (checkWin(board, currentPlayer)) {
                            statusLabel.setText("Player " + (currentPlayer == 1 ? "X" : "O") + " has won!");
                            disableAllButtons();
                        } 
                        // Check for draw
                        else if (isBoardFull()) {
                            statusLabel.setText("It's a draw!");
                        } 
                        else {
                            // Switch player
                            currentPlayer = (currentPlayer == 1) ? 2 : 1;
                            statusLabel.setText("Player " + (currentPlayer == 1 ? "X" : "O") + "'s turn");
                        }
                    }
                }
            }
        }
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }

    private boolean checkWin(int[][] b, int player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (b[i][0] == player && b[i][1] == player && b[i][2] == player) return true;
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (b[0][j] == player && b[1][j] == player && b[2][j] == player) return true;
        }
        // Check diagonals
        if (b[0][0] == player && b[1][1] == player && b[2][2] == player) return true;
        if (b[0][2] == player && b[1][1] == player && b[2][0] == player) return true;

        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe();
            game.setVisible(true);
        });
    }
}
