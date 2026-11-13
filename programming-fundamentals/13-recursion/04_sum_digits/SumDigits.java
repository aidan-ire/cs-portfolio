import java.util.*;

public class SumDigits{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a number: ");
        int num = sc.nextInt();

        System.out.println("Sum of the digits of " + num + ": " + sumDigits(Math.abs(num)));     // the sign isn't a digit

        sc.close();
    }

    /*
    the last digit is num % 10 and num / 10 strips it off, so the sum is the last digit
    plus the sum of the rest. one call per digit, so O(log n).
    eg. sumDigits(472) = 2 + sumDigits(47) = 2 + 7 + sumDigits(4) = 2 + 7 + 4 + sumDigits(0) = 13
    */
    public static int sumDigits(int num){
        if(num == 0) return 0;     // base case

        return num % 10 + sumDigits(num / 10);
    }
}
