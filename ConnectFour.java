import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConnectFour extends JFrame implements ActionListener {

    private final int ROWS = 6;
    private final int COLS = 7;
    private final int CELL_SIZE = 90;

    private int[][] board = new int[ROWS][COLS];
    private int currentPlayer = 1;
    private boolean gameOver = false;

    private JLabel statusLabel;
    private JButton resetButton;
    private GamePanel panel;

    public ConnectFour() {
        setTitle("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new GamePanel();
        add(panel, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(new Color(20, 20, 50));

        statusLabel = new JLabel("🔴 Red's Turn");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));
        bottom.add(statusLabel, BorderLayout.CENTER);

        resetButton = new JButton("RESET");
        resetButton.addActionListener(this);
        bottom.add(resetButton, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);

        setSize(COLS * CELL_SIZE, ROWS * CELL_SIZE + 80);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (gameOver) return;
                int col = e.getX() / CELL_SIZE;
                makeMove(col);
                panel.repaint();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        resetGame();
    }

    private void makeMove(int col) {
        if (col < 0 || col >= COLS) return;

        for (int r = ROWS - 1; r >= 0; r--) {
            if (board[r][col] == 0) {
                board[r][col] = currentPlayer;

                int winner = checkWinner();
                if (winner != 0) {
                    gameOver = true;
                    JOptionPane.showMessageDialog(this,
                            (winner == 1 ? "Red" : "Yellow") + " Wins!");
                } else if (isBoardFull()) {
                    gameOver = true;
                    JOptionPane.showMessageDialog(this, "Draw!");
                } else {
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                    statusLabel.setText(currentPlayer == 1 ? "🔴 Red's Turn" : "🟡 Yellow's Turn");
                }
                return;
            }
        }
    }

    private void resetGame() {
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++)
                board[r][c] = 0;
        currentPlayer = 1;
        gameOver = false;
        statusLabel.setText("🔴 Red's Turn");
        panel.repaint();
    }

    private boolean isBoardFull() {
        for (int c = 0; c < COLS; c++)
            if (board[0][c] == 0) return false;
        return true;
    }

    private int checkWinner() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                int p = board[r][c];
                if (p == 0) continue;

                if (c + 3 < COLS &&
                        p == board[r][c + 1] &&
                        p == board[r][c + 2] &&
                        p == board[r][c + 3]) return p;

                if (r + 3 < ROWS &&
                        p == board[r + 1][c] &&
                        p == board[r + 2][c] &&
                        p == board[r + 3][c]) return p;

                if (r + 3 < ROWS && c + 3 < COLS &&
                        p == board[r + 1][c + 1] &&
                        p == board[r + 2][c + 2] &&
                        p == board[r + 3][c + 3]) return p;

                if (r - 3 >= 0 && c + 3 < COLS &&
                        p == board[r - 1][c + 1] &&
                        p == board[r - 2][c + 2] &&
                        p == board[r - 3][c + 3]) return p;
            }
        }
        return 0;
    }

    class GamePanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(10, 30, 80));
            g.fillRect(0, 0, getWidth(), getHeight());

            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    int x = c * CELL_SIZE;
                    int y = r * CELL_SIZE;

                    g.setColor(Color.WHITE);
                    g.fillOval(x + 10, y + 10, CELL_SIZE - 20, CELL_SIZE - 20);

                    if (board[r][c] == 1) {
                        g.setColor(Color.RED);
                        g.fillOval(x + 10, y + 10, CELL_SIZE - 20, CELL_SIZE - 20);
                    } else if (board[r][c] == 2) {
                        g.setColor(Color.YELLOW);
                        g.fillOval(x + 10, y + 10, CELL_SIZE - 20, CELL_SIZE - 20);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new ConnectFour();
    }
}
