import java.util.*;

public class BreakPalindromicNumbers{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a number: ");
        int start = sc.nextInt();

        if(start < 0) start = 0;    // negative numbers are not palindromic

        int num = start;
        while(true){
            if(isPalindrome(num)) break;    // stops the search at the first palindromic number
            num++;
        }

        System.out.println("The first palindromic number from " + start + " is " + num);

        sc.close();
    }

    public static boolean isPalindrome(int num){
        int revNum = 0;
        int temp = num;

        while(temp > 0){
            revNum = revNum * 10 + temp % 10;   // moves the last digit of temp onto revNum
            temp = temp / 10;
        }

        return revNum == num;
    }
}
