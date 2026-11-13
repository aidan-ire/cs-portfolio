import java.io.*;

public class Power{
    public static long sink = 0; // results are added to this so the timed calls can't be skipped as unused

    /*
    a^n = a * a^(n-1), so the method multiplies a by the answer for n - 1 and
    keeps calling itself until n gets down to 0.
    eg. power(2,3) -> 2 * power(2,2) -> 2 * 2 * power(2,1) -> 2 * 2 * 2 * power(2,0) = 8
    this is O(n) multiplications, and it also needs n calls waiting on the stack
    at the same time, which is what causes a StackOverflowError when n is too big.
    */
    public static long powerRecursive(long a, int n) {
        if(n == 0) return 1; // base case, anything to the power of 0 is 1

        return a * powerRecursive(a, n - 1);
    }

    /*
    same n multiplications as the recursive version, but done in a loop so it
    only needs one variable no matter how big n is.
    this is O(n) time and O(1) space.
    */
    public static long powerIterative(long a, int n) {
        long res = 1;

        for(int i = 0; i < n; i++){
            res = res * a;
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        // small values first to check both versions give the right answer
        System.out.println("2^10: recursive " + powerRecursive(2, 10) + ", iterative " + powerIterative(2, 10));
        System.out.println("3^13: recursive " + powerRecursive(3, 13) + ", iterative " + powerIterative(3, 13));
        System.out.println("7^0:  recursive " + powerRecursive(7, 0) + ", iterative " + powerIterative(7, 0));
        System.out.println();

        int n = 20000;
        long a = 2;

        long start = System.nanoTime();
        powerRecursive(a, n);
        long end = System.nanoTime();
        System.out.println("Recursive time: " + (end - start) + " ns");

        start = System.nanoTime();
        powerIterative(a, n);
        end = System.nanoTime();
        System.out.println("Iterative time: " + (end - start) + " ns");

        timingTable(a);
        findStackLimit(a);
    }

    /*
     * timingTable
     * times both versions for n = 2000, 4000, ..., 20000 and writes the results
     * to power_timing.csv. one call is too quick to time reliably, so each
     * version is called many times and the average is taken.
     * Arguments:
     *   a - the base to use
     */
    public static void timingTable(long a) throws IOException {
        int repeats = 2000;

        // run both a few times first so the timings aren't of the very first calls
        for(int i = 0; i < repeats; i++){
            sink += powerRecursive(a, 5000);
            sink += powerIterative(a, 5000);
        }

        PrintWriter out = new PrintWriter("power_timing.csv");
        out.println("n,recursive_ns,iterative_ns");

        System.out.println();
        System.out.println("n\trecursive (ns)\titerative (ns)");
        for(int n = 2000; n <= 20000; n += 2000){
            long start = System.nanoTime();
            for(int i = 0; i < repeats; i++) sink += powerRecursive(a, n);
            long end = System.nanoTime();
            long recTime = (end - start) / repeats;

            start = System.nanoTime();
            for(int i = 0; i < repeats; i++) sink += powerIterative(a, n);
            end = System.nanoTime();
            long itTime = (end - start) / repeats;

            System.out.println(n + "\t" + recTime + "\t\t" + itTime);
            out.println(n + "," + recTime + "," + itTime);
        }
        out.close();
    }

    /*
     * findStackLimit
     * keeps doubling n until the recursive version runs out of stack, to show
     * roughly where the StackOverflowError happens. the iterative version is run
     * with the same n to show it has no such limit.
     * Arguments:
     *   a - the base to use
     */
    public static void findStackLimit(long a) {
        int n = 20000;

        System.out.println();
        while(true){
            try{
                powerRecursive(a, n);
                System.out.println("n = " + n + ": recursive ok");
                n = n * 2;
            }catch(StackOverflowError e){
                System.out.println("n = " + n + ": recursive StackOverflowError");
                break;
            }
        }

        powerIterative(a, n);
        System.out.println("n = " + n + ": iterative ok");
    }
}
