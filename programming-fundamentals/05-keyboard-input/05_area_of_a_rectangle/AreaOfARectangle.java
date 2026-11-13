import java.util.*;

public class AreaOfARectangle{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the length and width: ");
        double length = sc.nextDouble();
        double width = sc.nextDouble();

        System.out.println("Area: " + (length * width));

        sc.close();
    }
}
