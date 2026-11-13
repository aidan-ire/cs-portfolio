import java.util.*;

public class UniqueSorting{
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

        selectionSort(arr);
        int[] unique = removeDuplicates(arr);

        System.out.println("Unique values, sorted: " + Arrays.toString(unique));

        sc.close();
    }

    public static void selectionSort(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            int min = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[min]) min = j;
            }

            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    /*
    after sorting, equal values are next to each other, so a value is a duplicate
    exactly when it matches the one before it. that turns finding duplicates into a single pass.
    arr must already be sorted
    */
    public static int[] removeDuplicates(int[] arr){
        int count = 1;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] != arr[i - 1]) count++;
        }

        int[] res = new int[count];
        res[0] = arr[0];
        int j = 1;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] != arr[i - 1]){
                res[j] = arr[i];
                j++;
            }
        }

        return res;
    }
}
