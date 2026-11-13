import java.util.*;

public class MathsFunction{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input n: ");
        int n = sc.nextInt();

        if(n < 1){
            System.out.println("n must be at least 1");
        }else{
            System.out.println("f(x) = x^2 - 3x + 2");
            printTable(1, n);
        }

        sc.close();
    }

    public static int f(int x){
        return x * x - 3 * x + 2;
    }

    /*
    prints f(x) for x up to n without a loop. each call prints one row and then calls itself
    with the next x, and the base case stops once x passes n.
    eg. printTable(1,3) -> printTable(2,3) -> printTable(3,3) -> printTable(4,3) stops
    */
    public static void printTable(int x, int n){
        if(x > n) return;      // base case

        System.out.println("f(" + x + ") = " + f(x));
        printTable(x + 1, n);
    }
}
