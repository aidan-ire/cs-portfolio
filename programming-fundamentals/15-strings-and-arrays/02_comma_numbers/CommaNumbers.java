import java.util.*;

public class CommaNumbers{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a whole number: ");
        long num = sc.nextLong();

        System.out.println("With commas: " + addCommas(num));

        sc.close();
    }

    /*
    works on the digits as a string, from the right. a comma goes in front of a digit
    whenever the number of digits already copied is a multiple of 3, eg. 1234567 -> 1,234,567.
    the minus sign is taken off first so it is never counted as a digit
    */
    public static String addCommas(long num){
        String digits = "" + num;
        String sign = "";
        if(digits.charAt(0) == '-'){
            sign = "-";
            digits = digits.substring(1);
        }

        String res = "";
        int copied = 0;
        for(int i = digits.length() - 1; i >= 0; i--){
            if(copied > 0 && copied % 3 == 0) res = "," + res;
            res = digits.charAt(i) + res;
            copied++;
        }

        return sign + res;
    }
}
