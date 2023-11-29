import java.util.Arrays;

public class CSPNQueens {

    public static void main(String[] args) {
        solveNQueens(8);
    }

    public static void solveNQueens(int n) {
        int[] placement = new int[n];
        Arrays.fill(placement, -1);

        if (placeQueens(placement, 0, n)) {
            printSolution(placement);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static boolean placeQueens(int[] placement, int row, int n) {
        if (row == n) {
            return true;  // All queens are placed successfully
        }

        for (int col = 0; col < n; col++) {
            if (isValidPlacement(placement, row, col)) {
                placement[row] = col;

                if (placeQueens(placement, row + 1, n)) {
                    return true;
                }

                placement[row] = -1;  // Backtrack
            }
        }

        return false;
    }

    private static boolean isValidPlacement(int[] placement, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (placement[i] == col ||            // Check if the same column is already occupied
                    placement[i] - i == col - row ||  // Check if it is on the same diagonal
                    placement[i] + i == col + row) {  // Check if it is on the same diagonal
                return false;
            }
        }
        return true;
    }

    private static void printSolution(int[] placement) {
        int n = placement.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (placement[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
