import java.util.*;

public class Factorial{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a number (0-20): ");
        int n = sc.nextInt();

        if(n < 0 || n > 20){
            System.out.println("The number must be between 0 and 20");
        }else{
            System.out.println(n + "! = " + factorial(n));
        }

        sc.close();
    }

    // multiplies 1 * 2 * ... * n, 0! is 1 because the loop never runs.
    // a long overflows after 20! so main only allows 0-20
    public static long factorial(int n){
        long res = 1;

        for(int i = 2; i <= n; i++){
            res = res * i;
        }

        return res;
    }
}
