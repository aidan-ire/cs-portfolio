import java.util.*;

public class CommonDigit{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input two numbers: ");
        long a = sc.nextLong();
        long b = sc.nextLong();

        boolean[] inA = digitsIn(Math.abs(a));
        boolean[] inB = digitsIn(Math.abs(b));

        String common = "";
        for(int d = 0; d <= 9; d++){
            if(inA[d] && inB[d]) common = common + d + " ";
        }

        if(common.equals("")){
            System.out.println(a + " and " + b + " have no digits in common");
        }else{
            System.out.println("Digits in both " + a + " and " + b + ": " + common.trim());
        }

        sc.close();
    }

    // marks which of the digits 0-9 appear in num, using % 10 and / 10 to go through them.
    // the do-while makes sure 0 itself counts as having the digit 0
    public static boolean[] digitsIn(long num){
        boolean[] seen = new boolean[10];

        do{
            seen[(int)(num % 10)] = true;
            num = num / 10;
        }while(num > 0);

        return seen;
    }
}
