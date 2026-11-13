import java.util.*;

public class FillDiagonal{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the size of the grid: ");
        int n = sc.nextInt();

        int[][] grid = new int[n][n];   // every value starts as 0
        for(int i = 0; i < n; i++){
            grid[i][i] = 1;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}
