import java.io.*;

public class sieve{
    public static int counter=0;

    public static void main(String[] args) throws IOException{
        int limit = 1000;

        // part 1, list and count the primes up to limit
        boolean[] prime = sieveOfEratosthenes(limit);
        int count = 0;

        System.out.println("Prime numbers up to " + limit + ":");
        for(int i = 2; i <= limit; i++){
            if(prime[i]){
                System.out.print(i + " ");
                count++;
            }
        }
        System.out.println();
        System.out.println("Number of primes up to " + limit + ": " + count);

        // part 2, run the sieve for limit = 50, 100, ..., 1000 and record the counter
        PrintWriter out = new PrintWriter("counter_results.csv");
        out.println("limit,counter");

        System.out.println();
        System.out.println("limit\tcounter");
        for(int n = 50; n <= 1000; n += 50){
            counter = 0; // reset so each run is counted on its own
            sieveOfEratosthenes(n);
            System.out.println(n + "\t" + counter);
            out.println(n + "," + counter);
        }
        out.close();
    }

    /*
    sieve of eratosthenes. every number from 2 to n starts off marked as prime,
    then for each prime p the multiples of p are crossed out. crossing out can
    start at p * p because any smaller multiple of p (2p, 3p, ...) has a smaller
    factor and was already crossed out by that one. for the same reason p only
    has to go up to the square root of n.
    eg. n = 30 -> p = 2 crosses out 4, 6, 8, ..., p = 3 crosses out 9, 12, 15, ...,
    p = 5 crosses out 25, 30, and 7 * 7 is past 30 so everything left is prime.
    counter goes up once for every number crossed out (some are crossed out more
    than once, eg. 12 by both 2 and 3).
    this is O(n log log n), each prime p does about n / p steps in the inner loop
    and the sum of 1 / p over the primes grows like log log n.
    */
    public static boolean[] sieveOfEratosthenes(int n){
        boolean[] prime = new boolean[n + 1];
        for(int i = 2; i <= n; i++) prime[i] = true; // 0 and 1 are not prime

        for(int p = 2; p * p <= n; p++){
            if(prime[p]){ // p was never crossed out so it is prime
                for(int i = p * p; i <= n; i += p){
                    prime[i] = false;
                    counter++;
                }
            }
        }

        return prime;
    }
}
