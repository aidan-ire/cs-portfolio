import java.util.*;

public class DoubleSize{
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

        int[] bigger = doubleSize(arr);

        System.out.println("Original (length " + arr.length + "): " + arrayToString(arr));
        System.out.println("Doubled  (length " + bigger.length + "): " + arrayToString(bigger));

        sc.close();
    }

    // returns a new array twice as long as arr with arr's values copied to the front.
    // the second half is left as 0s, arr itself is not changed
    public static int[] doubleSize(int[] arr){
        int[] res = new int[arr.length * 2];

        for(int i = 0; i < arr.length; i++){
            res[i] = arr[i];
        }

        return res;
    }

    public static String arrayToString(int[] arr){
        String out = "[";
        for(int i = 0; i < arr.length; i++){
            out = out + arr[i];
            if(i < arr.length - 1) out = out + ", ";
        }
        return out + "]";
    }
}
