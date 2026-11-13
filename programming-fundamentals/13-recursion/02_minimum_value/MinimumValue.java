import java.util.*;

public class MinimumValue{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the number of values: ");
        int n = sc.nextInt();
        if(n < 1){
            System.out.println("The array needs at least one value");
            sc.close();
            return;
        }

        int[] arr = new int[n];
        System.out.print("Please input the values: ");
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("Minimum value: " + min(arr, 0));

        sc.close();
    }

    /*
    the smallest value from index i onwards is whichever is smaller of arr[i] and the smallest
    value after it. the base case is the last element, which is the minimum of itself.
    this makes one call per element so it is O(n)
    */
    public static int min(int[] arr, int i){
        if(i == arr.length - 1) return arr[i];      // base case

        int restMin = min(arr, i + 1);
        if(arr[i] < restMin) return arr[i];
        return restMin;
    }
}
