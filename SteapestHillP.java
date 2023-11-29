import java.util.Arrays;
import java.util.Random;

public class SteapestHillP {
    private static final int[][] GOAL_STATE = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    };

    public static void main(String[] args) {
        int[][] initialBoard = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 0, 8}
        };

        solveEightPuzzle(initialBoard);
    }

    public static void solveEightPuzzle(int[][] initialBoard) {
        int[][] currentBoard = initialBoard;
        int currentCost = calculateCost(currentBoard);

        while (currentCost > 0) {
            int[][] bestNeighbor = null;
            int bestNeighborCost = currentCost;

            
            System.out.println("Possible Neighbors:");
            for (int i = 0; i < 4; i++) {
                int[][] neighbor = generateNeighbor(currentBoard, i);
                int neighborCost = calculateCost(neighbor);

          
                System.out.println("Neighbor " + i + ":");
                printBoard(neighbor);
                System.out.println("Cost: " + neighborCost);

                if (neighborCost < bestNeighborCost) {
                    bestNeighbor = neighbor;
                    bestNeighborCost = neighborCost;
                }
            }

            if (bestNeighborCost < currentCost) {
                currentBoard = bestNeighbor;
                currentCost = bestNeighborCost;
                System.out.println("Selected Neighbor:");
                printBoard(currentBoard);
                System.out.println("Cost: " + currentCost);
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

    public static int[][] generateNeighbor(int[][] board, int move) {
        int[][] neighbor = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(board[i], 0, neighbor[i], 0, 3);
        }

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

        int newRow = emptyRow + dx[move];
        int newCol = emptyCol + dy[move];

        if (isValidMove(newRow, newCol)) {
            
            neighbor[emptyRow][emptyCol] = board[newRow][newCol];
            neighbor[newRow][newCol] = 0;
        }

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

