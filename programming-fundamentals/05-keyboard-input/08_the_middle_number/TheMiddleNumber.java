import java.util.*;

public class TheMiddleNumber{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input three numbers: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int middle;
        if((a >= b && a <= c) || (a <= b && a >= c)){
            middle = a;
        }else if((b >= a && b <= c) || (b <= a && b >= c)){
            middle = b;
        }else{
            middle = c;
        }

        System.out.println("The middle number is " + middle);

        sc.close();
    }
}
