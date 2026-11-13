import java.util.*;

public class Exponent{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter base and exponent respectively: ");
        long base = sc.nextLong();
        int exp = sc.nextInt();

        if(exp < 0){
            System.out.println("The exponent must be 0 or more");
        }else{
            String shown = base < 0 ? "(" + base + ")" : "" + base;     // so -3^5 isn't read as -(3^5)
            System.out.println(shown + "^" + exp + " = " + power(base, exp));
        }

        sc.close();
    }

    /*
    uses x^n = (x^(n/2))^2, with one extra x when n is odd, so the exponent halves each call.
    this is O(log n) rather than O(n) for multiplying n times.
    eg. power(2,10) -> power(2,5) -> power(2,2) -> power(2,1) -> power(2,0)
    the result overflows a long once it passes about 9.2 * 10^18
    */
    public static long power(long base, int exp){
        if(exp == 0) return 1;     // base case, anything to the power of 0 is 1

        long half = power(base, exp / 2);
        if(exp % 2 == 0) return half * half;
        return half * half * base;
    }
}
