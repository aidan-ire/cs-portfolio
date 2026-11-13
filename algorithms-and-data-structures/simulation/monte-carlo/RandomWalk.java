import java.util.*;

public class RandomWalk{
    public static void main(String[] args){
        Random rand = new Random();
        int steps = 20;

        // back at 0 needs exactly 10 steps each way, C(20,10) = 184756 walks out of 2^20 = 1048576
        double exact = 184756.0 / 1048576;

        System.out.println("Probability a 1D random walk is at position 0 after " + steps + " steps");
        System.out.printf("Exact: %.6f%n", exact);
        System.out.println();
        System.out.println("N\t\testimate\terror");

        for(int N = 100; N <= 10000000; N *= 10){
            double est = simulate(N, steps, rand);
            System.out.printf("%-10d\t%.6f\t%.6f%n", N, est, Math.abs(est - exact));
        }
    }

    /*
    monte carlo estimate. one trial is a walk that starts at 0 and moves +1 or -1
    at random on each step. it is a success if the walker is back at 0 at the
    end, and the estimate is successes / trials.
    the walker can only be at 0 after an even number of steps, since it needs the
    same number of steps in each direction.
    this is O(N * steps).
    */
    public static double simulate(int N, int steps, Random rand){
        int success = 0;

        for(int i = 0; i < N; i++){
            int pos = 0;

            for(int j = 0; j < steps; j++){
                if(rand.nextBoolean()){
                    pos++;
                }else{
                    pos--;
                }
            }

            if(pos == 0) success++;
        }

        return (double)success / N;
    }
}
