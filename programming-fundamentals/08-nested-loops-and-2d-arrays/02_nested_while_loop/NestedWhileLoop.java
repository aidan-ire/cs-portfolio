import java.util.*;

public class NestedWhileLoop{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the number of rows: ");
        int rows = sc.nextInt();

        int row = 1;
        while(row <= rows){
            int col = 1;
            while(col <= row){
                System.out.print(col + " ");
                col++;
            }
            System.out.println();
            row++;
        }

        sc.close();
    }
}
