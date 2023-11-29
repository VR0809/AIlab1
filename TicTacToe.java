import java.util.*;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] xo = new char[3][3];
        int[] arr = new int[9];
        int n = 9, k = 0, c1 = 0, c2 = 0, p = 8, sum = 0;

        System.out.print("Enter board position: ");
        
        do {
            c1 = 0;
            c2 = 0;
            
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    xo[i][j] = scanner.next().charAt(0);
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (xo[i][j] == 'x') {
                        arr[k++] = 1;
                        c1++;
                    } else if (xo[i][j] == 'o') {
                        arr[k++] = 2;
                        c2++;
                    } else if (xo[i][j] == '-') {
                        arr[k++] = 0;
                    }
                }
            }

            if (Math.abs(c1 - c2) != 1) {
                System.out.print("Enter valid xo position: ");
            }

        } while (Math.abs(c1 - c2) != 1);

        System.out.println("\nVector: ");
        for (int i = 0; i < 9; i++) {
            sum += arr[i] * Math.pow(3, p--);
            System.out.print(arr[i] + " ");
            System.out.println("\nIndexAtI: "+ i +" " + sum);
        }

        System.out.println("\nIndex: " + sum);

        scanner.close();
    }
}


