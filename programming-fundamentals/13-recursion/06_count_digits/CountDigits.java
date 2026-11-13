import java.util.*;

public class CountDigits{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a number: ");
        long num = sc.nextLong();

        int count = countDigits(Math.abs(num));
        if(count == 1){
            System.out.println(num + " has 1 digit");
        }else{
            System.out.println(num + " has " + count + " digits");
        }

        sc.close();
    }

    /*
    each call strips the last digit with / 10 and adds 1. the base case is a single digit,
    which also makes 0 count as one digit rather than none.
    eg. countDigits(905) = 1 + countDigits(90) = 1 + 1 + countDigits(9) = 3
    */
    public static int countDigits(long num){
        if(num < 10) return 1;     // base case

        return 1 + countDigits(num / 10);
    }
}
