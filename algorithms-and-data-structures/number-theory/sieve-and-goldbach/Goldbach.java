import java.util.*;

public class Goldbach{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please input an even number greater than 2: ");
        int n = sc.nextInt();
        sc.close();

        if(n <= 2 || n % 2 != 0){
            System.out.println(n + " is not an even number greater than 2");
            return;
        }

        // one sieve up to n covers every smaller even number as well
        boolean[] prime = sieve.sieveOfEratosthenes(n);

        int p = goldbach(n, prime);
        System.out.println(n + " = " + p + " + " + (n - p));

        // check every even number from 4 up to n, not just n itself
        boolean holds = true;
        for(int i = 4; i <= n; i += 2){
            if(goldbach(i, prime) == -1){
                System.out.println("No two primes add up to " + i);
                holds = false;
            }
        }
        if(holds) System.out.println("The conjecture holds for every even number from 4 to " + n);
    }

    /*
    finds two primes that add up to n. i try each prime p from 2 up to n / 2 and
    check if n - p is prime as well, which is just a lookup in the sieve array.
    going past n / 2 would only find the same pairs the other way around.
    eg. n = 14 -> 2 + 12 no, 3 + 11 yes -> 14 = 3 + 11
    returns the smaller prime of the pair, or -1 if there is no pair.
    this is O(n) for one number once the sieve has been built.
    */
    public static int goldbach(int n, boolean[] prime){
        for(int p = 2; p <= n / 2; p++){
            if(prime[p] && prime[n - p]) return p;
        }

        return -1;
    }
}
