import java.io.*;
import java.util.*;

public class arraysearch{
    public static void main(String[] args) throws IOException{
        Random rand = new Random();

        // one search of each kind on a big array, timed as in the lab
        int n = 100000000;
        int[] arr = fillArray(n);
        int target = rand.nextInt(n); // the array holds 0 to n - 1 so the target is always in it

        long start = System.nanoTime();
        int pos = linearSearch(arr, target);
        long end = System.nanoTime();
        long elapsedTime = (end - start) / 1_000_000;
        System.out.println("Linear search: found " + target + " at index " + pos);
        System.out.println("Execution time: " + elapsedTime + " ms");

        start = System.nanoTime();
        pos = binarySearch(arr, target);
        end = System.nanoTime();
        elapsedTime = (end - start) / 1_000_000;
        System.out.println("Binary search: found " + target + " at index " + pos);
        System.out.println("Execution time: " + elapsedTime + " ms (" + (end - start) + " ns)");

        arr = null; // let the big array go before the table is built

        // table of average times for different array sizes
        int linearRuns = 200;    // each run searches for a different random target
        int binaryRuns = 100000; // one binary search is far too quick to time, so many more of them
        PrintWriter out = new PrintWriter("search_timing.csv");
        out.println("n,linear_ms,binary_ns");

        System.out.println();
        System.out.println("n\t\tlinear (ms)\tbinary (ns)");
        for(n = 10000000; n <= 100000000; n += 10000000){
            arr = fillArray(n);

            // targets are picked before the clock starts so only the searching is timed
            int[] targets = new int[binaryRuns];
            for(int i = 0; i < binaryRuns; i++) targets[i] = rand.nextInt(n);

            long wrong = 0; // arr[i] = i, so a search is right if the index equals the target

            start = System.nanoTime();
            for(int i = 0; i < linearRuns; i++){
                if(linearSearch(arr, targets[i]) != targets[i]) wrong++;
            }
            end = System.nanoTime();
            double linearMs = (end - start) / 1_000_000.0 / linearRuns;

            start = System.nanoTime();
            for(int i = 0; i < binaryRuns; i++){
                if(binarySearch(arr, targets[i]) != targets[i]) wrong++;
            }
            end = System.nanoTime();
            double binaryNs = (double)(end - start) / binaryRuns;

            if(wrong != 0) System.out.println(wrong + " searches gave the wrong index for n = " + n);

            System.out.printf("%d\t%.3f\t\t%.0f%n", n, linearMs, binaryNs);
            out.printf("%d,%.3f,%.0f%n", n, linearMs, binaryNs);
        }
        out.close();
    }

    // fills an array of size n with 0, 1, 2, ..., n - 1 so it is always sorted
    public static int[] fillArray(int n){
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = i;
        return arr;
    }

    /*
    linear search looks at every element from the start until it finds the
    target, so it works on any array, sorted or not.
    this is O(n), on average the target is half way along so about n / 2
    comparisons, and n if it is last or not there at all.
    */
    public static int linearSearch(int[] arr, int target){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == target) return i;
        }

        return -1; // not found
    }

    /*
    binary search only works on a sorted array. i look at the middle element, and
    if the target is smaller it can only be in the left half, if it is bigger it
    can only be in the right half, so half of what is left is thrown away each time.
    eg. looking for 6 in 0..9 -> mid 4, go right -> mid 7, go left -> mid 5, go
    right -> mid 6, found
    this is O(log n) as the range halves on every step, about 27 steps for
    n = 100,000,000.
    */
    public static int binarySearch(int[] arr, int target){
        int low = 0;
        int high = arr.length - 1;

        while(low <= high){
            int mid = low + (high - low) / 2; // same as (low + high) / 2 but can't overflow

            if(arr[mid] == target) return mid;

            if(arr[mid] < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }

        return -1; // not found
    }
}
