import java.util.*;

public class RightAngleTriangles{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the height: ");
        int height = sc.nextInt();

        for(int row = 1; row <= height; row++){
            for(int col = 1; col <= row; col++){
                System.out.print("*");
            }
            System.out.println();
        }

        sc.close();
    }
}
