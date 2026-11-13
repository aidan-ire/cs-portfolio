import java.util.*;

public class Reciprocals{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input n: ");
        int n = sc.nextInt();

        if(n < 1){
            System.out.println("n must be at least 1");
        }else{
            System.out.printf("%s = %.4f%n", describeSum(n), sumReciprocals(n));
        }

        sc.close();
    }

    // writes out every term for a short sum and uses ... for a long one
    public static String describeSum(int n){
        if(n > 4) return "1/1 + 1/2 + ... + 1/" + n;

        String res = "1/1";
        for(int i = 2; i <= n; i++){
            res = res + " + 1/" + i;
        }
        return res;
    }

    /*
    the sum up to 1/n is 1/n plus the sum up to 1/(n-1), with 1/1 = 1 as the base case.
    1.0 is used so the division isn't integer division (1/2 would be 0).
    makes n calls, so a very large n would run out of stack
    */
    public static double sumReciprocals(int n){
        if(n == 1) return 1.0;     // base case

        return 1.0 / n + sumReciprocals(n - 1);
    }
}
