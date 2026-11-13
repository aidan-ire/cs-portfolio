import java.util.*;

public class CoinFlip{
    public static void main(String[] args){
        Random rand = new Random();
        double exact = 120.0 / 1024; // C(10,7) = 120 ways out of 2^10 = 1024 outcomes

        System.out.println("Probability of exactly 7 heads in 10 flips");
        System.out.printf("Exact: %.6f%n", exact);
        System.out.println();
        System.out.println("N\t\testimate\terror");

        for(int N = 100; N <= 10000000; N *= 10){
            double est = simulate(N, rand);
            System.out.printf("%-10d\t%.6f\t%.6f%n", N, est, Math.abs(est - exact));
        }
    }

    /*
    monte carlo estimate. one trial is 10 coin flips, and it counts as a success
    if exactly 7 of them are heads. the estimate is the number of successes
    divided by the number of trials, which gets closer to the real probability
    as N grows (the error shrinks roughly like 1 / sqrt(N)).
    this is O(N), each trial is a fixed 10 flips.
    */
    public static double simulate(int N, Random rand){
        int success = 0;

        for(int i = 0; i < N; i++){
            int heads = 0;

            for(int j = 0; j < 10; j++){
                if(rand.nextBoolean()) heads++; // true counts as heads
            }

            if(heads == 7) success++;
        }

        return (double)success / N;
    }
}
