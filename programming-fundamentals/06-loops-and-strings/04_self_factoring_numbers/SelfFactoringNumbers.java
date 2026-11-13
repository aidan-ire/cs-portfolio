import java.util.*;

public class SelfFactoringNumbers{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a limit: ");
        int limit = sc.nextInt();

        String result = "";
        for(int i = 1; i <= limit; i++){
            if(isSelfFactoring(i)) result = result + i + " ";
        }

        System.out.println("Self-factoring numbers up to " + limit + ": " + result.trim());

        sc.close();
    }

    // true if every digit of num is non-zero and divides num exactly, eg. 128 (1, 2 and 8 all divide it)
    public static boolean isSelfFactoring(int num){
        int temp = num;

        while(temp > 0){
            int digit = temp % 10;
            if(digit == 0 || num % digit != 0) return false;
            temp = temp / 10;
        }

        return true;
    }
}
