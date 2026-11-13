import java.util.*;

public class PrimePalindrome{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a limit: ");
        int limit = sc.nextInt();

        String result = "";
        for(int i = 2; i <= limit; i++){
            if(isPalindrome(i) && isPrime(i)) result = result + i + " ";
        }

        if(result.equals("")){
            System.out.println("There are no prime palindromes up to " + limit);
        }else{
            System.out.println("Prime palindromes up to " + limit + ": " + result.trim());
        }

        sc.close();
    }

    public static boolean isPrime(int num){
        if(num < 2) return false;

        for(int i = 2; i * i <= num; i++){
            if(num % i == 0) return false;
        }

        return true;
    }

    // builds the number backwards digit by digit and compares it with the original
    // eg. 131 -> 1 -> 13 -> 131
    public static boolean isPalindrome(int num){
        int temp = num;
        int revNum = 0;

        while(temp > 0){
            revNum = revNum * 10 + temp % 10;
            temp = temp / 10;       // strips the last digit
        }

        return revNum == num;
    }
}
