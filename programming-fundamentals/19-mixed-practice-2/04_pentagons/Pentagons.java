import java.util.*;

public class Pentagons{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("How many pentagonal numbers? ");
        int n = sc.nextInt();

        if(n < 1){
            System.out.println("Please ask for at least 1");
        }else{
            String res = "";
            for(int i = 1; i <= n; i++){
                res = res + pentagonal(i) + " ";
            }
            if(n == 1){
                System.out.println("The first pentagonal number: " + res.trim());
            }else{
                System.out.println("The first " + n + " pentagonal numbers: " + res.trim());
            }
        }

        sc.close();
    }

    /*
    the nth pentagonal number is n(3n - 1)/2, the number of dots in n nested pentagons.
    n(3n - 1) is always even (if n is odd, 3n - 1 is even) so the division is exact.
    a long is used because n(3n - 1) passes the int limit once n is about 27,000
    */
    public static long pentagonal(long n){
        return n * (3 * n - 1) / 2;
    }
}
