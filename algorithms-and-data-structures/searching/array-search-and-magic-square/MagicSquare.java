import java.util.Scanner;

public class MagicSquare{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of matrix (n): ");
        int n = sc.nextInt();

        if(n <= 0){
            System.out.println("The size must be at least 1");
            sc.close();
            return;
        }

        int[][] square = new int[n][n];

        System.out.println("Enter matrix elements:");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.println("Enter Element at row " + (i + 1) + ", column " + (j + 1)); // rows and columns are shown from 1
                square[i][j] = sc.nextInt();
            }
        }
        sc.close();

        System.out.println("You entered:");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(square[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        if(isMagic(square)){
            System.out.println("The square is a magic square!");
        }else{
            System.out.println("The square is NOT a magic square!");
        }
    }

    /*
    a square is magic if every row, every column and both diagonals add up to the
    same number. i take the sum of the first row as the target, then check each
    row and column against it, and the two diagonals at the end. as soon as one
    sum is different it can't be magic.
    eg. 2 7 6 / 9 5 1 / 4 3 8 -> every row, column and diagonal is 15
    this is O(n^2) as every element is read a fixed number of times.
    */
    public static boolean isMagic(int[][] square){
        int n = square.length;

        int target = 0;
        for(int j = 0; j < n; j++) target += square[0][j];

        for(int i = 0; i < n; i++){
            int rowSum = 0;
            int colSum = 0;

            for(int j = 0; j < n; j++){
                rowSum += square[i][j]; // row i
                colSum += square[j][i]; // column i
            }

            if(rowSum != target || colSum != target) return false;
        }

        int diag1 = 0; // top left to bottom right
        int diag2 = 0; // top right to bottom left
        for(int i = 0; i < n; i++){
            diag1 += square[i][i];
            diag2 += square[i][n - 1 - i];
        }

        return diag1 == target && diag2 == target;
    }
}
