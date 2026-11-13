import java.util.*;

public class NestedForLoop{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the size of the table: ");
        int n = sc.nextInt();

        for(int row = 1; row <= n; row++){
            for(int col = 1; col <= n; col++){
                System.out.printf("%4d", row * col);
            }
            System.out.println();
        }

        sc.close();
    }
}
