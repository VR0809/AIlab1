import java.util.Scanner;

public class TicTacToe235Strategy {
    public static void main(String[] args) {
        char[][] board = {
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };

        char currentPlayer = 'X';
        int moves = 0;

        while (true) {
            displayBoard(board);
            int move;

            if (currentPlayer == 'X') {
                move = getHumanMove(board);
            } else {
                move = get235StrategyMove(board);
            }

            if (move == -1) {
                System.out.println("It's a draw!");
                break;
            }

            int row = (move - 1) / 3;
            int col = (move - 1) % 3;

            if (board[row][col] != 'X' && board[row][col] != 'O') {
                board[row][col] = currentPlayer;
                moves++;

                if (checkWin(board, currentPlayer)) {
                    displayBoard(board);
                    System.out.println(currentPlayer + " wins!");
                    break;
                } else if (moves == 9) {
                    displayBoard(board);
                    System.out.println("It's a draw!");
                    break;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    public static void displayBoard(char[][] board) {
        System.out.println("  " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("  ---------");
        System.out.println("  " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("  ---------");
        System.out.println("  " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }

    public static int getHumanMove(char[][] board) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your move (1-9): ");
        int move = scanner.nextInt();
        return move;
    }

    public static int get235StrategyMove(char[][] board) {
        // First, check if the center square is available (5).
        if (board[1][1] == '5') {
            return 5;
        }

        // Next, check if any of the corners (2, 4, 6, 8) are available.
        for (int i = 2; i <= 8; i += 2) {
            if (isAvailable(board, i)) {
                return i;
            }
        }

        // If none of the above is available, take any available edge (1, 3, 7, 9).
        for (int i = 1; i <= 9; i += 2) {
            if (isAvailable(board, i)) {
                return i;
            }
        }

        // If no moves are available, return -1 to indicate a draw.
        return -1;
    }

    public static boolean isAvailable(char[][] board, int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        return board[row][col] != 'X' && board[row][col] != 'O';
    }

    public static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Check rows
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Check columns
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Check diagonals
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Check diagonals
        }
        return false;
    }
}
