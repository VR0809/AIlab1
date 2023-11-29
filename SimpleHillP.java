import java.util.Arrays;
import java.util.Random;

public class SimpleHillP {
    private static final int[][] GOAL_STATE = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    };

    public static void main(String[] args) {
        int[][] initialBoard = {
            {1, 2, 3},
            {4, 5, 6},
            {0, 7, 8}
        };

        solveEightPuzzle(initialBoard);
    }

    public static void solveEightPuzzle(int[][] initialBoard) {
        int[][] currentBoard = initialBoard;
        int currentCost = calculateCost(currentBoard);

        while (currentCost > 0) {
            int[][] neighbor = generateNeighbor(currentBoard);
            int neighborCost = calculateCost(neighbor);

            if (neighborCost < currentCost) {
                currentBoard = neighbor;
                currentCost = neighborCost;
                System.out.println("Cost: " + currentCost);
                printBoard(currentBoard);
            } else {
                System.out.println("Stuck at local minimum.");
                break;
            }
        }

        if (currentCost == 0) {
            System.out.println("Puzzle solved!");
            printBoard(currentBoard);
        } else {
            System.out.println("No solution found.");
        }
    }

    public static int calculateCost(int[][] board) {
        int cost = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != GOAL_STATE[i][j]) {
                    cost++;
                }
            }
        }
        return cost;
    }

    public static int[][] generateNeighbor(int[][] board) {
        int[][] neighbor = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(board[i], 0, neighbor[i], 0, 3);
        }

        Random rand = new Random();
        int emptyRow = -1, emptyCol = -1;

        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                    break;
                }
            }
        }

      
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int randIdx=0;
        do {
            for(int i=0;i<4;i++){
                randIdx=i;

            }
            
        } while (!isValidMove(emptyRow + dx[randIdx], emptyCol + dy[randIdx]));

        // Swap the empty tile with the neighboring tile
        neighbor[emptyRow][emptyCol] = board[emptyRow + dx[randIdx]][emptyCol + dy[randIdx]];
        neighbor[emptyRow + dx[randIdx]][emptyCol + dy[randIdx]] = 0;

        return neighbor;
    }

    public static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3;
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

