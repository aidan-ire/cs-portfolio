import java.util.*;

public class StarTriangle{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a height: ");
        int height = sc.nextInt();

        printTriangle(height);

        sc.close();
    }

    // prints a centred triangle of stars, row i has 2i - 1 stars.
    // each row is pushed right by height - i spaces so the rows line up in the middle
    public static void printTriangle(int height){
        for(int i = 1; i <= height; i++){
            for(int j = 0; j < height - i; j++){
                System.out.print(" ");
            }
            for(int j = 0; j < 2 * i - 1; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
