import java.util.*;

public class TicTacToe {

    static char[][] b = new char[3][3];
    static char p = 'X';
    static Scanner s = new Scanner(System.in);

    static void reset() {
        for (int i = 0; i < 3; i++)
            Arrays.fill(b[i], ' ');
    }

    static void show() {
        System.out.println("\n   1   2   3");
        for (int i = 0; i < 3; i++) {
            System.out.printf("%d  %c | %c | %c%n", i + 1, b[i][0], b[i][1], b[i][2]);
            if (i < 2) System.out.println("  ---+---+---");
        }
        System.out.println();
    }

    static boolean win(char c) {
        for (int i = 0; i < 3; i++) {
            if ((b[i][0] == c && b[i][1] == c && b[i][2] == c) ||
                (b[0][i] == c && b[1][i] == c && b[2][i] == c))
                return true;
        }
        return (b[0][0] == c && b[1][1] == c && b[2][2] == c) ||
               (b[0][2] == c && b[1][1] == c && b[2][0] == c);
    }

    static boolean full() {
        for (char[] r : b)
            for (char c : r)
                if (c == ' ') return false;
        return true;
    }

    public static void main(String[] args) {
        reset();
        System.out.println(" TRENDY TIC TAC TOE ✨");
        System.out.println(" Player X vs Player O");

        while (true) {
            show();
            System.out.print(" Player " + p + " enter row & col (e.g., 1 3): ");
            int r = s.nextInt() - 1;
            int c = s.nextInt() - 1;

            if (r < 0 || r > 2 || c < 0 || c > 2 || b[r][c] != ' ') {
                System.out.println(" Invalid move, try again!");
                continue;
            }

            b[r][c] = p;

            if (win(p)) {
                show();
                System.out.println(" Player " + p + " WINS! ");
                break;
            }

            if (full()) {
                show();
                System.out.println(" It's a DRAW!");
                break;
            }

            p = (p == 'X') ? 'O' : 'X';
        }

        System.out.println(" Game Over. Thanks for playing!");
    }
}
