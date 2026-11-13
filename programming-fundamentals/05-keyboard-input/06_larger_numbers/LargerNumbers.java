import java.util.*;

public class LargerNumbers{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input two numbers: ");
        int a = sc.nextInt();
        int b = sc.nextInt();

        if(a == b){
            System.out.println("The numbers are equal");
        }else if(a > b){
            System.out.println("The larger number is " + a);
        }else{
            System.out.println("The larger number is " + b);
        }

        sc.close();
    }
}
