import java.util.Scanner;

public class TicTacAI {
    static int draw = 0, turn;
    static char[][] xo = new char[3][3];

    static int rowScore(int r, int c) {
        for (int i = 0; i < 3; i++) {
            if (turn == 1 && xo[r][i] == 'o') {
                System.out.println("row: " + 0 + " ");
                return 0;
            }
            if (turn == 2 && xo[r][i] == 'x') {
                return 0;
            }
        }
        System.out.print("row: " + 1 + " ");
        return 1;
    }

    static int colScore(int r, int c) {
        for (int i = 0; i < 3; i++) {
            if (turn == 1 && xo[i][c] == 'o') {
                System.out.println("col: " + 0 + " ");
                return 0;
            }
            if (turn == 2 && xo[i][c] == 'x') {
                return 0;
            }
        }
        System.out.print("col: " + 1 + " ");
        return 1;
    }

    static int diaScore(int r, int c) {
        int x = 0;
        if (r == c) {
            if (turn == 1 && (xo[0][0] != 'o' && xo[1][1] != 'o' && xo[2][2] != 'o')) {
                x++;
            }
            if (turn == 2 && (xo[0][0] != 'x' && xo[1][1] != 'x' && xo[2][2] != 'x')) {
                x++;
            }
        }
        if ((r == 1 && c == 1) || Math.abs(r - c) == 2) {
            if (turn == 1 && (xo[0][2] != 'o' && xo[1][1] != 'o' && xo[2][0] != 'o')) {
                x++;
            }
            if (turn == 2 && (xo[0][2] != 'x' && xo[1][1] != 'x' && xo[2][0] != 'x')) {
                x++;
            }
        }
        System.out.print("dia: " + x + " ");
        return x;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = 9, k = 0, c1, c2;
        int[] arr = new int[9];
        
        System.out.print("Enter board position: ");
        do {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    xo[i][j] = scanner.next().charAt(0);
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (xo[i][j] == 'x') {
                        arr[k++] = 1;
                    } else if (xo[i][j] == 'o') {
                        arr[k++] = 2;
                    } else if (xo[i][j] == '-') {
                        arr[k++] = 0;
                    }
                }
            }

            c1 = 0;
            c2 = 0;

            for (int i = 0; i < n; i++) {
                if (arr[i] == 1) {
                    c1++;
                } else if (arr[i] == 2) {
                    c2++;
                }
            }

            if (Math.abs(c1 - c2) != 1 && Math.abs(c1 - c2) != 0) {
                System.out.println("Enter valid board position: " + c1 + " " + c2);
            }
            k = 0;

        } while (Math.abs(c1 - c2) != 1 && Math.abs(c1 - c2) != 0);

        if (Math.abs(c1 - c2) != 0) {
            turn = 1;
        }

        System.out.print("vector: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        turn = (c1 > c2) ? 2 : 1;

        int spaces = 9 - (c1 + c2);
        int[][] m = new int[spaces][9];
        int pos = 0;
        int[][] win = new int[3][3];
        int[] res = new int[spaces];
        int cnt = 0;
        int finalScore = 1;

        for (int i = 0; i < spaces; i++) {
            for (int j = 0; j < 9; j++) {
                if (arr[j] == 0) {
                    cnt++;
                }
                if (finalScore == cnt && arr[j] == 0) {
                    pos = j;
                    m[i][j] = turn;
                } else {
                    m[i][j] = arr[j];
                }
                System.out.print(m[i][j] + " ");
            }
            
            System.out.println("\n");
            cnt = 0;
            int r = pos / 3;
            int c = pos % 3;
            res[i] = rowScore(r, c) + colScore(r, c) + diaScore(r, c);
            System.out.println();
            finalScore++;
        }

        System.out.print("Scores: ");
        for (int i = 0; i < spaces; i++) {
            System.out.print(res[i] + " ");
        }

        scanner.close();
    }
}

