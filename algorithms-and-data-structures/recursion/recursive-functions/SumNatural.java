import java.util.*;

public class SumNatural{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please input a number: ");
        int n = sc.nextInt();
        sc.close();

        if(n < 0){
            System.out.println("The number can't be negative");
            return;
        }

        System.out.println("Sum of 1 to " + n + " = " + sum(n));
        System.out.println("Formula n(n+1)/2 = " + (long)n * (n + 1) / 2);
    }

    /*
    the sum of the first n numbers is n plus the sum of the first n - 1 numbers,
    so the method calls itself with n - 1 until it gets down to 0.
    eg. sum(4) -> 4 + sum(3) -> 4 + 3 + sum(2) -> 4 + 3 + 2 + sum(1) -> 4 + 3 + 2 + 1 + sum(0) = 10
    this is O(n), one call for each number from n down to 0.
    */
    public static long sum(int n){
        if(n <= 0) return 0; // base case, <= so a negative n can't recurse forever

        return n + sum(n - 1);
    }
}
