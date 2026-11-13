import java.util.*;

public class IntegerInputs{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input two numbers: ");
        int a = sc.nextInt();
        int b = sc.nextInt();

        System.out.println("Sum: " + (a + b));

        sc.close();
    }
}
