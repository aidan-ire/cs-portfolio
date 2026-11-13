import java.util.*;

public class FactorialSigma{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a number from 1 to 20: ");
        int n = sc.nextInt();

        if(n < 1 || n > 20){    // 21! no longer fits in a long
            System.out.println("The number must be from 1 to 20");
        }else{
            long sum = 0;
            for(int i = 1; i <= n; i++){
                long factorial = 1;
                for(int j = 2; j <= i; j++){
                    factorial = factorial * j;
                }
                sum = sum + factorial;
            }
            System.out.println("Sum of 1! to " + n + "! = " + sum);
        }

        sc.close();
    }
}
