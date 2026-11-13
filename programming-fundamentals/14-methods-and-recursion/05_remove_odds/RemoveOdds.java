import java.util.*;

public class RemoveOdds{
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

        int[] evens = removeOdds(arr);

        System.out.println("Before: " + Arrays.toString(arr));
        System.out.println("After:  " + Arrays.toString(evens));

        sc.close();
    }

    /*
    arrays can't shrink, so the evens are counted first to size the new array
    and then copied across in their original order. arr is not changed
    */
    public static int[] removeOdds(int[] arr){
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] % 2 == 0) count++;
        }

        int[] res = new int[count];
        int j = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] % 2 == 0){
                res[j] = arr[i];
                j++;
            }
        }

        return res;
    }
}
